package is6610;

import java.util.List;

public class DataModel {

	String fileName;
	String path;
	long fileSize;
	String status;
	List<DataBlock> dataBlock;
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the fileSize
	 */
	public long getFileSize() {
		return fileSize;
	}
	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the dataBlcok
	 */
	public List<DataBlock> getDataBlock() {
		return dataBlock;
	}
	/**
	 * @param dataBlcok the dataBlcok to set
	 */
	public void setDataBlcok(List<DataBlock> dataBlock) {
		this.dataBlock = dataBlock;
	}

}
