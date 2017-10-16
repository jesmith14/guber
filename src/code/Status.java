package code;

public enum Status { 

	  AVAILABLE, ENROUTE, ARRIVED, INTRANSIT, FINISHED;

	  public String toString() {
	    switch(this) {
	      case AVAILABLE: return "available";
	      case ENROUTE: return "enroute";
	      case ARRIVED: return "arrived";
	      case INTRANSIT: return "intransit";
	      case FINISHED: return "finished";
	      default:       return "offline";
	    }
	  }
	}
