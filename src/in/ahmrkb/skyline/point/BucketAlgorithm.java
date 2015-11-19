package in.ahmrkb.skyline.point;

import in.ahmrkb.prj.Database;
import in.ahmrkb.prj.ProjectValues;
import in.ahmrkb.prj.SkylineAlgorithm;
import in.ahmrkb.prj.Tuple;

import java.util.ArrayList;
import java.util.HashMap;

public class BucketAlgorithm extends SkylineAlgorithm {
	@Override
	public ArrayList<Tuple> getSkylineTuples() {
		bucketizeTuples();
		ArrayList<Tuple> candidateSkylineList = new ArrayList<>();
		for (Bucket bucket : allBuckets) {
			if (!bucket.getBucketContents().isEmpty()
					&& bucket.getBitmap() != 0) {
				candidateSkylineList.addAll(getLocalSkylines(bucket));
			}
		}
		return null;
	}

	private ArrayList<Tuple> getLocalSkylines(Bucket bucket) {
		BNLAlgorithm bnlAlgo = new BNLAlgorithm();
		return bnlAlgo.findSkylineSet(
				bucket.getBucketContents(),
				getQueryDimensions(bucket.getBitmap(), bucket
						.getBucketContents().get(0).getDimensions()));
	}

	private void bucketizeTuples() {
		Tuple current;
		Bucket bucket;
		Integer bitmap;
		for (int i = 0, len = Database.size(); i < len; i++) {
			current = Database.get(i);
			bitmap = computeBitMapForTuple(current);
			bucket = bitmapBucketMapping.get(bitmap);
			if (bucket == null) {
				bucket = new Bucket(bitmap);
				bitmapBucketMapping.put(bitmap, bucket);
				allBuckets.add(bucket);
			}
			bucket.add(current);
			System.out.println(bitmap);
		}
	}

	private boolean doesFirstDominateSecond(Tuple first, Tuple second,
			ArrayList<Integer> checkDims) {
		boolean firstIsBetterOrEqual = true, secondIsBetterOrEqual = true;

		return false;
	}

	private int computeBitMapForTuple(Tuple tuple) {
		int bitmap = 0;
		for (int i = 0, len = tuple.getDimensions(); i < len; i++) {
			if (tuple.get(i) == ProjectValues.MISSING_VAL_REPR) {
				bitmap = (bitmap << 1) ^ 0x0;
			} else {
				bitmap = (bitmap << 1) ^ 0x1;
			}
		}
		return bitmap;
	}

	private ArrayList<Integer> getQueryDimensions(int bitmap, int dimensions) {
		ArrayList<Integer> queryDims = new ArrayList<>();
		for (int i = 1; i <= dimensions; i++) {
			if ((bitmap & 0x1) != 0) {
				queryDims.add(dimensions - i);
			}
			bitmap >>= 1;
		}
		return queryDims;

	}

	private HashMap<Integer, Bucket> bitmapBucketMapping = new HashMap<>();
	private ArrayList<Bucket> allBuckets = new ArrayList<>();
}
