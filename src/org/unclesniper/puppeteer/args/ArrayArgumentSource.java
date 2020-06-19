package org.unclesniper.puppeteer.args;

public class ArrayArgumentSource implements ArgumentSource {

	private String[] arguments;

	private int offset;

	private String name;

	private int locationBias;

	private String locationPrefix;

	private String locationSuffix;

	public ArrayArgumentSource(String[] arguments, int offset, String name) {
		this(arguments, offset, name, 0, null, null);
	}

	public ArrayArgumentSource(String[] arguments, int offset, String name, int locationBias,
			String locationPrefix, String locationSuffix) {
		this.arguments = arguments;
		setOffset(offset);
		this.name = name;
		this.locationBias = locationBias;
		this.locationPrefix = locationPrefix;
		this.locationSuffix = locationSuffix;
	}

	public String[] getArguments() {
		return arguments;
	}

	public void setArguments(String[] arguments) {
		this.arguments = arguments;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		if(offset < 0)
			throw new IllegalArgumentException("Offset cannot be negative: " + offset);
		this.offset = offset;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLocationBias() {
		return locationBias;
	}

	public void setLocationBias(int locationBias) {
		this.locationBias = locationBias;
	}

	public String getLocationPrefix() {
		return locationPrefix;
	}

	public void setLocationPrefix(String locationPrefix) {
		this.locationPrefix = locationPrefix;
	}

	public String getLocationSuffix() {
		return locationSuffix;
	}

	public void setLocationSuffix(String locationSuffix) {
		this.locationSuffix = locationSuffix;
	}

	@Override
	public String current() {
		if(arguments == null || offset >= arguments.length)
			return null;
		return arguments[offset];
	}

	@Override
	public void next() {
		if(arguments != null && offset < arguments.length)
			++offset;
	}

	@Override
	public String location() {
		if(name == null)
			return null;
		return name + ':' + (locationPrefix == null ? "" : locationPrefix) + (offset + locationBias)
				+ (locationSuffix == null ? "" : locationSuffix);
	}

}
