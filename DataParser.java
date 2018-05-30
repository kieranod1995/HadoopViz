package is6610;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataParser {

	public static void main(String[] args) {
		
		File hdfs = new File("HDFS_FSCK_BLOCK_LOCATIONS");
		Scanner scanner = new Scanner(hdfs);
		String textFull = scanner.useDelimiter("\\A").next();
		scanner.close();

		// Some variables used for parsing of the data.
		String newFileBlocks = "block(s): ";
		String newDataBlock = "BP-";
		String directory = "<dir>";
		String nameNodeConnection = "Connecting";
		String authetication = "(auth:SIMPLE)";

		// makes the text file an array and checks each line and removes
		// unneccessary ones.
		String[] textDirectory = textFull.split("\n");
		for (int x = 0; x < textDirectory.length; x++) {
			if ((textDirectory[x].contains(directory)) || (textDirectory[x].contains(nameNodeConnection))
					|| (textDirectory[x].contains(authetication))) {

				textDirectory[x] = "";

			} // if

		} // for

		// Removes some the lines about <dir> that needed to be ignored
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < textDirectory.length; i++) {
			strBuilder.append(textDirectory[i]);

		} // for

		// Rebuilds to a string.
		String text = strBuilder.toString();

		// Split each file into an element in the Array.
		String[] fileFSCK = text.split("\\R{2}");

		// For loop removes any excess lines that are not necessary.
		for (String s : fileFSCK) {

			List<DataBlock> list = new ArrayList<>();

			int i = 1;

			if (s.contains(newFileBlocks)) {

				String[] fileDataBlockInformation = s.split("\\r\n|\\n|\\r");

				// The first line containing DataModel information
				String fileInformationTrim = fileDataBlockInformation[0];
				String[] fileInformation = fileInformationTrim.split("\\s+");

				String fileName;
				String path;
				long fileSize;
				String status;

				// We will now find the last index of '/'
				int fileIndex = fileInformation[0].lastIndexOf("/");

				// Parse FileName
				fileName = fileInformation[0].substring(fileIndex + 1, fileInformation[0].length());

				// Parse FilePath
				path = fileInformation[0].substring(0, fileIndex + 1);

				// Find the fileSize in string and convert to Long
				String fileSizestr = fileInformation[1].substring(0, fileInformation[1].length());
				fileSize = Long.valueOf(fileSizestr);

				// Find the Status of the file.
				status = fileInformation[5];

				// Find how many blocks this file has.
				String blockNumbers = fileInformation[3];
				int blocks = Integer.parseInt(blockNumbers);

				// Create dm object and fill it in.

				// System.out.println(dm.getFileName())
				DataBlock db = new DataBlock();

				while (i <= blocks) {

					// Split the row at the space.
					String[] dataBlockToken = fileDataBlockInformation[i].split("\\s+");

					// Counts elements in array, for when replication happens.
					int numberOfElements = 0;
					for (String str : dataBlockToken) {

						numberOfElements++;
					}

					// This will find the datablock ID in order.
					String index = dataBlockToken[0];
					String blockIdxstr = index.replace(".", "");
					long blockIdx = Long.valueOf(blockIdxstr);

					String blockPoolBlk = dataBlockToken[1];
					String[] blockPoolIPID = blockPoolBlk.split(":");
					String blockPoolIP = blockPoolIPID[0];
					String[] blockPool = blockPoolIP.split("-");

					// Finds second element in array and assigns it to pool.
					String blockPoolId = blockPool[1];

					// Finds the IP
					String ipAddressNameNode = blockPool[2];

					// Finds blockId using split().
					String block = blockPoolIPID[1];
					String blockId = block.replaceAll("blk_", "");

					// Finds block size and converts it to a long value.
					String blockSizestr = dataBlockToken[2].replaceAll("len=", "");
					long blockSize = Long.valueOf(blockSizestr);

					String liveReplstr = dataBlockToken[3].replaceAll("Live_repl=", "");
					int liveRepl = Integer.parseInt(liveReplstr);

					// WORK ON THE REPLICATION

					for (int j = 4; j < numberOfElements; j++) {

						String dataNodeInfo = dataBlockToken[j];
						String[] dataNodeToken = dataNodeInfo.split(",");

						// datNodeToken[0] contains an IP and port number
						String dataIPPort = dataNodeToken[0];
						String ipPort1 = dataIPPort.replace("[DatanodeInfoWithStorage[", "");
						String ipPort2 = ipPort1.replace("DatanodeInfoWithStorage[", "");
						String ipPort = ipPort2.replace("],", "");
						String[] ipPortToken = ipPort.split(":");
						String ipAddressDataNode = ipPortToken[0];
						String dataNodePort = ipPortToken[1];

						// dataNodeToken[1] contains the DataNode ID and no
						// additional
						// parsing is required.
						String dataNodeId = dataNodeToken[1];

						// dataNodeToken[2] contains the storage type
						String type = dataNodeToken[2];
						String storageType = type.replace("]]", "");

						// System.out.println(fileName + "," + blockIdx + "," +
						// blockPoolId + "," + ipAddressNameNode
						// + blockId + "," + blockSize + "," + liveRepl + "," +
						// ipAddressDataNode + ","
						// + dataNodePort + "," + dataNodeId + "," +
						// storageType);

						// System.out.println(blockPoolId + " , " +
						// ipAddressNameNode);

						//db = new DataBlock(blockIdx, blockPoolId, ipAddressNameNode, blockId, blockSize, liveRepl,
						//		ipAddressDataNode, dataNodePort, dataNodeId, storageType);
						//list.add(db);
					}

					i++;

				} // while

				//DataModel dm = new DataModel(fileName, path, fileSize, status, list);
				//System.out.println(dm);
				//System.out.println(db);

			} // if

		} // for

	}// main

}

