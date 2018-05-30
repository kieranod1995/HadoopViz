package is6610;

import java.util.List;

public class DataNode {

	String ipAddress;
	String dataNodeId;
	List<DataBlock> dataBlocks;

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress
	 *            the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the dataNodeId
	 */
	public String getDataNodeId() {
		return dataNodeId;
	}

	/**
	 * @param dataNodeId
	 *            the dataNodeId to set
	 */
	public void setDataNodeId(String dataNodeId) {
		this.dataNodeId = dataNodeId;
	}

	/**
	 * @return the dataBlocks
	 */
	public List<DataBlock> getDataBlocks() {
		return dataBlocks;
	}

	/**
	 * @param dataBlocks
	 *            the dataBlocks to set
	 */
	public void setDataBlocks(List<DataBlock> dataBlocks) {
		this.dataBlocks = dataBlocks;
	}
	
}
