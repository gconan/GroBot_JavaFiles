package groBot.entity;

/**
 * This class represents the light bulb sockets and their timings
 * HardCoded - number of sockets, final int that can be changed if more or less sockets
 * There are on and off times for the lights that we will send to the Microcontroller to switch power
 * The Boolean array is for which light sockets are on/off
 * @author conangammel
 *
 */
public class Lights {
	/**number of light sockets on the GroBot*/
	private final int numSockets = 6;
	
	/**time to turn on 0645 = 6:45am*/
	private int onTime;
	
	/**time to turn on 1345 = 1:45pm*/
	private int offTime;
	
	/**on/off for each socket*/
	private Boolean[] sockets;
	
	public Lights(){
		this.offTime = 0;
		this.onTime = 0;
		this.sockets = new Boolean[numSockets];
	}
	
	public Lights(int onT, int offT, Boolean l1, Boolean l2, Boolean l3, Boolean l4, Boolean l5, Boolean l6){
		this.onTime = onT;
		this.offTime = offT;
		this.sockets = new Boolean[numSockets];
		this.sockets[0] = l1;
		this.sockets[1] = l2;
		this.sockets[2] = l3;
		this.sockets[3] = l4;
		this.sockets[4] = l5;
		this.sockets[5] = l6;
	}

}
