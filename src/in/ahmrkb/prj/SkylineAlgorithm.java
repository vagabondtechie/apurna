package in.ahmrkb.prj;

import java.util.ArrayList;

public abstract class SkylineAlgorithm {
	public abstract ArrayList<Tuple> getSkylineTuples();

	public static enum ALGOTYPE {
		PRIVATE, DEFAULT, NAIVE, BUCKET
	}
}
