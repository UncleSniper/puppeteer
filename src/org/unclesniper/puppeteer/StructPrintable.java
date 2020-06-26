package org.unclesniper.puppeteer;

import org.unclesniper.ogdl.Token;
import java.util.function.Consumer;

public interface StructPrintable {

	void printTo(StructSink sink);

	public static void beginObject(Object object, StructSink sink, boolean empty) {
		sink.print(object.getClass().getName());
		if(object instanceof Traceable)
			sink.print(Traceable.makeLocation((Traceable)object, " [defined at ", "]", ""));
		if(empty)
			sink.print(" {}");
		else {
			sink.println(" {");
			sink.indent();
		}
	}

	public static void endObject(StructSink sink) {
		sink.unindent();
		sink.print("}");
	}

	public static void list(String property, Iterable<? extends StructPrintable> elements, StructSink sink) {
		StructPrintable.anyList(property, elements, element -> element.printTo(sink), sink);
	}

	public static void enumConstant(String property, Enum<?> value, StructSink sink) {
		if(property != null)
			sink.print(property + " = ");
		sink.print(value == null ? "<null>" : value.name());
	}

	public static void booleanValue(String property, boolean value, StructSink sink) {
		if(property != null)
			sink.print(property + " = ");
		sink.print(value ? "true" : "false");
	}

	public static void string(String property, String value, StructSink sink) {
		if(property != null)
			sink.print(property + " = ");
		if(value == null)
			sink.print("<null>");
		else {
			sink.print("\"");
			sink.print(Token.escapeString(value));
			sink.print("\"");
		}
	}

	public static <T> void anyList(String property, Iterable<? extends T> elements, Consumer<? super T> print,
			StructSink sink) {
		if(property != null)
			sink.print(property + " = ");
		sink.print("[");
		boolean had = false;
		for(T element : elements) {
			if(had)
				sink.print(",");
			else {
				had = true;
				sink.indent();
			}
			sink.endl();
			print.accept(element);
		}
		if(had) {
			sink.endl();
			sink.unindent();
		}
		sink.print("]");
	}

	public static void stringList(String property, Iterable<String> elements, StructSink sink) {
		StructPrintable.anyList(property, elements, element -> StructPrintable.string(null, element, sink), sink);
	}

}
