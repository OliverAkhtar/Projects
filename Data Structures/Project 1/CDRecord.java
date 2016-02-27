/*The CDRecord class is a class that represents the CD records that the librarian
 * using the program wishes to represent and store.*/

public class CDRecord 
{
	private String AlbumTitle;
	private String Artist;
	private int numOfTracks;
	private long libraryTrackingNumber;
	private int numOfCopies;
	
	
	public CDRecord(String title, String artistName, int trackCount,
			        long libTrackNum, int copyCount)
	{
		AlbumTitle = title;
		Artist = artistName;
		numOfTracks = trackCount;
		libraryTrackingNumber = libTrackNum;
		numOfCopies = copyCount;
	}
	
	public String toString(){
		return ("Album Title:" + AlbumTitle + "\n" + "Artist:" + Artist +
				"\n" + "Number of Tracks:" + numOfTracks + "\n" +
				"LibraryTrackingNumber:" + libraryTrackingNumber + "\n" +
				"Number of Copies:" + numOfCopies);
	}

	public String getTitle()
	{
		return AlbumTitle;
	}
	
	public long getTracking()
	{
		return libraryTrackingNumber;
	}
	
	public int getNumOfCopies()
	{
		return numOfCopies;
	}
	
	public void addCopy()
	{
		numOfCopies++;
	}
	
	public void removeCopy()
	{
		numOfCopies--;
	}
}
