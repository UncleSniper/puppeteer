package org.unclesniper.puppeteer;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;
import org.unclesniper.ogdl.Injection;
import org.unclesniper.ogdl.ClassRegistry;
import org.unclesniper.ogdl.FileURLResolver;
import org.unclesniper.ogdl.TokenSinkWrapper;
import org.unclesniper.ogdl.DirectiveTokenSink;
import org.unclesniper.ogdl.ResourceURLResolver;
import org.unclesniper.ogdl.ObjectGraphDescriptor;
import org.unclesniper.puppeteer.resource.Resources;
import org.unclesniper.puppeteer.args.ArgumentSource;
import org.unclesniper.ogdl.ObjectDescriptionException;
import org.unclesniper.puppeteer.args.ArrayArgumentSource;

public class Launcher {

	public static final class Config {

		public File confdir;

		public String networksFile;

		public File plandir;

		public boolean verbose;

		public boolean nativeTrace;

		public Config() {}

		public File getConfdir() {
			return confdir == null ? Launcher.DEFAULT_CONFDIR : confdir;
		}

		public String getNetworksFileName() {
			return networksFile == null || networksFile.length() == 0
					? Launcher.DEFAULT_NETWORKS_FILE : networksFile;
		}

		public File getNetworksFileFile() {
			String name = getNetworksFileName();
			if(name.indexOf(File.separatorChar) < 0 && name.indexOf('/') < 0)
				return new File(getConfdir(), name);
			return new File(name);
		}

		public File getPlandir() {
			return plandir == null ? new File(getConfdir(), "plans") : plandir;
		}

	}

	private static final class PendingArgument {

		final String name;

		final Consumer<String> sink;

		public PendingArgument(String name, Consumer<String> sink) {
			this.name = name;
			this.sink = sink;
		}

	}

	private static final String LAUNCH_COMMAND;

	private static final File USER_HOME = new File(System.getProperty("user.home"));

	private static final File DEFAULT_DOTDIR = new File(Launcher.USER_HOME, ".puppeteer");

	private static final File DEFAULT_CONFDIR = Launcher.DEFAULT_DOTDIR;

	private static final String DEFAULT_NETWORKS_FILE = "networks.ogdl";

	static {
		String cname = Launcher.class.getName();
		String launchCommand = System.getProperty(cname + ".launchCommand");
		if(launchCommand != null)
			launchCommand = launchCommand.trim();
		if(launchCommand == null || launchCommand.length() == 0)
			LAUNCH_COMMAND = "java " + cname;
		else
			LAUNCH_COMMAND = launchCommand;
	}

	public static void main(String[] args) {
		System.exit(Launcher.flowMain(args));
	}

	public static int flowMain(String[] args) {
		Config config = new Config();
		PendingArgument parg = null;
		boolean help = false;
		int offset;
		for(offset = 0; offset < args.length; ++offset) {
			String arg = args[offset];
			if(parg != null) {
				parg.sink.accept(arg);
				parg = null;
				continue;
			}
			int alen = arg.length();
			if(alen == 0 || arg.charAt(0) != '-')
				break;
			if(alen > 1 && arg.charAt(1) == '-') {
				if(alen == 2) {
					++offset;
					break;
				}
				String opt, optarg;
				int eqpos = arg.indexOf('=', 2);
				if(eqpos < 0) {
					opt = arg.substring(2);
					optarg = null;
				}
				else if(eqpos == 2) {
					System.err.println("Error: A long command line option needs a name; "
							+ "the '--' cannot be followed directly by '=': " + arg);
					return 1;
				}
				else {
					opt = arg.substring(2, eqpos);
					optarg = arg.substring(eqpos + 1);
				}
				switch(opt) {
					case "confdir":
						parg = new PendingArgument("--confdir", value -> {
							config.confdir = value.length() == 0 ? null : new File(value);
						});
						break;
					case "networks":
						parg = new PendingArgument("--networks", value -> {
							config.networksFile = value;
						});
						break;
					case "plandir":
						parg = new PendingArgument("--plandir", value -> {
							config.plandir = value.length() == 0 ? null : new File(value);
						});
						break;
					case "help":
						if(optarg != null) {
							System.err.println("Error: Command line option '--help' does not take an argument.");
							return 1;
						}
						help = true;
						break;
					case "verbose":
						if(optarg != null) {
							System.err.println("Error: Command line option '--verbose' does not take an argument.");
							System.err.println("       Run with '--help' for usage.");
							return 1;
						}
						config.verbose = true;
						break;
					case "trace":
						if(optarg != null) {
							System.err.println("Error: Command line option '--trace' does not take an argument.");
							System.err.println("       Run with '--help' for usage.");
							return 1;
						}
						config.nativeTrace = true;
						break;
					default:
						System.err.println("Error: Unrecognized command line option: --" + opt);
						System.err.println("       Run with '--help' for usage.");
						return 1;
				}
				if(optarg != null) {
					parg.sink.accept(optarg);
					parg = null;
				}
			}
			else {
				if(alen == 1)
					break;
				int cindex;
			  optChars:
				for(cindex = 1; cindex < alen; ++cindex) {
					char opt = arg.charAt(cindex);
					switch(opt) {
						case 'c':
							parg = new PendingArgument("-c", value -> {
								config.confdir = value.length() == 0 ? null : new File(value);
							});
							++cindex;
							break optChars;
						case 'n':
							parg = new PendingArgument("-n", value -> {
								config.networksFile = value;
							});
							++cindex;
							break optChars;
						case 'p':
							parg = new PendingArgument("-p", value -> {
								config.plandir = value.length() == 0 ? null : new File(value);
							});
							++cindex;
							break optChars;
						case 'v':
							config.verbose = true;
							break;
						case 't':
							config.nativeTrace = true;
							break;
						default:
							System.err.println("Error: Unrecognized command line option: -" + opt);
							System.err.println("       Run with '--help' for usage.");
							return 1;
					}
				}
				if(cindex < alen) {
					parg.sink.accept(arg.substring(cindex));
					parg = null;
				}
			}
		}
		if(parg != null) {
			System.err.println("Error: Command line option '" + parg.name + "' requires an argument.");
			System.err.println("       Run with '--help' for usage.");
			return 1;
		}
		if(help) {
			Launcher.usage();
			return 0;
		}
		return Launcher.confMain(config, args, offset);
	}

	private static void usage() {
		for(String line : new String[] {
			"Puppeteer, your friendly neighborhood server task automation tool",
			"Usage: " + Launcher.LAUNCH_COMMAND + " [options] <plan-name> [plan-args...]",
			"Options:",
			"  --confdir=PATH     Set the configuration directory to PATH.",
			"                     If unset or set empty, defaults to '"
					+ Launcher.DEFAULT_CONFDIR.getAbsolutePath() + "'.",
			"  -c PATH            Same as '--confdir'.",
			"  --networks=NAME    Set the NAME of the networks (World) wiring file.",
			"                     If this does not contain any file separators,",
			"                     considered relative to configuration directory.",
			"                     If unset or set empty, defaults to '" + Launcher.DEFAULT_NETWORKS_FILE + "'.",
			"  -n NAME            Same as '--networks'.",
			"  --plandir=PATH     Set the plan directory to PATH.",
			"                     If unset or set empty, defaults to '$confdir" + File.separatorChar + "plans',",
			"                     where '$confdir' is the directory set by '--confdir'.",
			"  -p PATH            Same as '--plandir'.",
			"  --verbose          Print steps as they are executed.",
			"  -v                 Same as '--verbose'.",
			"  --trace            Print Java-level stack traces for exceptions.",
			"  -t                 Same as '--trace'.",
			"  --help             Print this helpful message and quit.",
		})
			System.out.println(line);
	}

	public static int confMain(Config config, String[] argv, int argOffset) {
		if(argOffset >= argv.length) {
			System.err.println("Error: No plan name given on command line.");
			System.err.println("       Run with '--help' for usage.");
			return 1;
		}
		File networksFile = config.getNetworksFileFile();
		String planName = argv[argOffset++] + ".ogdl";
		File planFile = new File(planName);
		if(!planFile.isAbsolute())
			planFile = new File(config.getPlandir(), planName);
		Injection inj = new Injection(new ClassRegistry());
		inj.registerBuiltinStringClassMappers();
		inj.addTokenSinkWrapper(Launcher.makeTokenSinkWrapper(config));
		ObjectGraphDescriptor worldDesc = Launcher.readDescription(inj, networksFile);
		if(worldDesc == null)
			return 1;
		Object worldObj = worldDesc.getRootObject();
		if(!(worldObj instanceof World)) {
			System.err.println("Error: Expected a " + World.class.getName() + " from '" + networksFile.getPath()
					+ "', but got a " + worldObj.getClass().getName());
			return 1;
		}
		ObjectGraphDescriptor planDesc = Launcher.readDescription(inj, planFile);
		if(planDesc == null)
			return 1;
		Object planObj = planDesc.getRootObject();
		if(!(planObj instanceof Plan)) {
			System.err.println("Error: Expected a " + Plan.class.getName() + " from '" + planFile.getPath()
					+ "', but got a " + planObj.getClass().getName());
			return 1;
		}
		ArrayArgumentSource argsrc = new ArrayArgumentSource(argv, argOffset, "<command line>", 0, "<word #", ">");
		ConsoleUI ui = new ConsoleUI();
		ui.setVerbose(config.verbose);
		ui.setNativeTrace(config.nativeTrace);
		return Launcher.wireMain((World)worldObj, (Plan)planObj, argsrc, ui);
	}

	private static TokenSinkWrapper makeTokenSinkWrapper(Config config) {
		DirectiveTokenSink.Wrapper wrapper = new DirectiveTokenSink.Wrapper();
		wrapper.setIncludeURLResolver("conf", new FileURLResolver(config.getConfdir()));
		wrapper.setIncludeURLResolver("plan", new FileURLResolver(config.getPlandir()));
		wrapper.setIncludeURLResolver("builtin", new ResourceURLResolver(Resources.class));
		return wrapper;
	}

	private static ObjectGraphDescriptor readDescription(Injection inj, File file) {
		try {
			return inj.readDescription(file);
		}
		catch(IOException ioe) {
			System.err.print("I/O error reading '" + file.getPath() + "': ");
			System.err.flush();
			ioe.printStackTrace();
		}
		catch(ObjectDescriptionException ode) {
			System.err.print("OGDL error in '" + file.getPath() + "': ");
			System.err.flush();
			ode.printStackTrace();
		}
		return null;
	}

	public static int wireMain(World world, Plan plan, ArgumentSource argsrc, PuppeteerUI ui) {
		try {
			plan.perform(ui, world, null, argsrc);
			return 0;
		}
		catch(PuppetException pe) {
			ui.error(pe);
			return 1;
		}
	}

}
