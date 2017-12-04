package net.mwa.common;

public enum MemberShipType {

		INDEPENDENT(Values.INDEPENDENT),
		APARTMENT(Values.APARTMENT),
		COMMERCIAL(Values.COMMERCIAL);

	    private final String memberShipType;

	    MemberShipType(final String memberShipType) {
	        this.memberShipType = memberShipType;
	    }

	    @Override
	    public String toString() {
	        return memberShipType;
	    }

	    public static MemberShipType convert(String value) {
	        for (MemberShipType channelType : values()) {
	            if (channelType.memberShipType.equals(value)) {
	                return channelType;
	            }
	        }
	        throw new IllegalArgumentException(value);
	    }

	    public static class Values {
	        public static final String INDEPENDENT = "INDEPENDENT";
	        public static final String APARTMENT = "APARTMENT";
	        public static final String COMMERCIAL = "COMMERCIAL";
	        public static final String VALUES = INDEPENDENT + "," + APARTMENT + "," + COMMERCIAL ; // the only way in static context
	    }
}
