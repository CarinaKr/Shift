package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import window.WindowView;

/**<dd>
* <h3><i> Levels </i></h3>
* 
* in this class all the important variables for all levels are initialized and saved
* 
 * @author Carina
 *
 */
public class Levels {
	
	private int zSize=40;
	private Feld[][] hLevel1Black,hLevel1White,hLevel2Black,hLevel2White;
	private BufferedImage[] zLevel1,zLevel2;
	private int[][] zPlayerPosition={{0,5},{14,6}}; //14,6
	/**
	 * int[Level][X/Y]
	 */
	private int[][] zDoorBlack={{8,6},{14,11}};	//Level, X/Y
	/**
	 * int[Level][X/Y]
	 */
	private int[][] zDoorWhite={{14-8,14-6},{14-14,14-11}};	//Level, X/Y
	/**
	 * int[Level][X/Y]
	 */
	private int[][] zKey1Black={{4,12},{0,3}};	//Level, X/Y
	/**
	 * int[Level][X/Y]
	 */
	private int[][] zKey1White={{14-4,14-12},{14-0,14-3}};	//Level, X/Y
	/**
	 * int[Level][X/Y]
	 */
	private int[][] zKey2Black={{},{10,3}};	//Level, X/Y
	/**
	 * int[Level][X/Y]
	 */
	private int[][] zKey2White={{},{14-10,14-3}};	//Level, X/Y
	/**
	 * int[Level][X/Y]
	 */
	private int[][] zKey3Black={{},{0,12}};	//Level, X/Y
	/**
	 * int[Level][X/Y]
	 */
	private int[][] zKey3White={{},{14-0,14-12}};	//Level, X/Y
	/**
	 * int[Level][Lage][Partnumber][X/Y]
	 */
	private int[][][][] zPlatform1Black={{{{9,12},{9,13},{9,14}},{{6,12},{7,12},{8,12}}},{{{6,2},{7,2},{8,2}},{{9,2},{10,2},{11,2}}}};  //Level, Lage,  Platformteil, X/Y
	/**
	 * int[Level][Lage][Partnumber][X/Y]
	 */
	private int[][][][] zPlatform1White={{{{14-9,14-12},{14-9,14-13},{14-9,14-14}},{{14-6,14-12},{14-7,14-12},{14-8,14-12}}},{{{14-6,14-2},{14-7,14-2},{14-8,14-2}},{{14-9,14-2},{14-10,14-2},{14-11,14-2}}}};  //Level, Lage,  Platformteil, X/Y
	/**
	 * int[Level][Lage][Partnumber][X/Y]
	 */
	private int[][][][] zPlatform2Black={{{{9,12},{9,13},{9,14}},{{6,12},{7,12},{8,12}}},{{{0,6},{1,7},{2,7}},{{1,5},{2,6},{3,6}}}};  //Level, Lage,  Platformteil, X/Y
	/**
	 * int[Level][Lage][Partnumber][X/Y]
	 */
	private int[][][][] zPlatform2White={{{{14-9,14-12},{14-9,14-13},{14-9,14-14}},{{14-6,14-12},{14-7,14-12},{14-8,14-12}}},{{{14-0,14-6},{14-1,14-7},{14-2,14-7}},{{14-1,14-5},{14-2,14-6},{14-3,14-6}}}};  //Level, Lage,  Platformteil, X/Y
	/**
	 * int[Level][Lage][Partnumber][X/Y]
	 */
	private int[][][][] zPlatform3Black={{{{9,12},{9,13},{9,14}},{{6,12},{7,12},{8,12}}},{{{8,10},{8,11},{8,12}},{{8,9},{8,8},{8,7}}}};  //Level, Lage,  Platformteil, X/Y
	/**
	 * int[Level][Lage][Partnumber][X/Y]
	 */
	private int[][][][] zPlatform3White={{{{14-9,14-12},{14-9,14-13},{14-9,14-14}},{{14-6,14-12},{14-7,14-12},{14-8,14-12}}},{{{14-8,14-10},{14-8,14-11},{14-8,14-12}},{{14-8,14-9},{14-8,14-8},{14-8,14-7}}}};  //Level, Lage,  Platformteil, X/Y
	private int[] zKeyNumbers={1,3}; //Anzahl der Schlüssel in Level1, in Level2
	/**
	 * int[Level][Partnumber][X/Y]
	 */
	private int[][][] zSpikesBlack={{},{{3,0},{9,1},{10,1},{11,1},{12,1},{8,14},{9,14}}};	//Level, Teil, X/Y
	/**
	 * int[Level][Partnumber][X/Y]
	 */
	private int[][][] zSpikesWhite={{},{{14-3,14-0},{14-9,14-1},{14-10,14-1},{14-11,14-1},{14-12,14-1},{14-8,14-14},{14-9,14-14}}};	//Level, Teil, X/Y
	
	/**<dd>
	 * <h3><i> Levels </i></h3>
	 * <p>
	 * <code>{@code public Levels()}</code>
	 * </p>
	 * Initializes the fields for all levels corresponding to the variables defined earlier and sets the correct
	 * values for each field
	 */
	public Levels()
	{
		zLevel1=new BufferedImage[3];
		zLevel2=new BufferedImage[3];
		try {
			zLevel1[0]=ImageIO.read(new File("Shift/images/Level1.png"));
			zLevel2[0]=ImageIO.read(new File("Shift/images/Level2.png")); 
			zLevel1[1]=ImageIO.read(new File("Shift/images/Level1Rot.png"));
			zLevel2[1]=ImageIO.read(new File("Shift/images/Level2Rot.png")); 
			zLevel1[2]=ImageIO.read(new File("Shift/images/Level1Blau.png"));
			zLevel2[2]=ImageIO.read(new File("Shift/images/Level2Blau.png")); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		hLevel1Black=new Feld[15][15];
		hLevel1White=new Feld[15][15];
		hLevel2Black=new Feld[15][15];
		hLevel2White=new Feld[15][15];
		//Level für Felder erstellen
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<15;j++)
			{
				hLevel1Black[i][j]=new Feld(i,j,zSize,true); //alle Felder erstellen und default auf weiß setzen
				hLevel1White[i][j]=new Feld(i,j,zSize,false);//alle Felder erstellen und default auf weiß setzen
				hLevel2Black[i][j]=new Feld(i,j,zSize,false); //alle Felder erstellen und default auf weiß setzen
				hLevel2White[i][j]=new Feld(i,j,zSize,true);
			}
		}
		
		
		
		//Level 1 Black
		for(int i=0;i<2;i++){for(int j=7;j<15;j++){
			hLevel1Black[i][j].setAcessable(false);
		}}
		for(int i=2;i<6;i++){for(int j=12;j<15;j++){
			hLevel1Black[i][j].setAcessable(false);
		}}
		for(int i=6;i<9;i++){
			hLevel1Black[i][14].setAcessable(false);
		}
		for(int i=9;i<13;i++){for(int j=12;j<15;j++){
			hLevel1Black[i][j].setAcessable(false);
		}}
		for(int j=7;j<15;j++){
			hLevel1Black[12][j].setAcessable(false);
		}
		for(int i=13;i<15;i++){for(int j=13;j<15;j++){
			hLevel1Black[i][j].setAcessable(false);
		}}
		hLevel1Black[8][7].setAcessable(false);
		hLevel1Black[9][7].setAcessable(false);
		
		
		//Level 1 White
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<15;j++)
			{
				hLevel1White[i][j].setAcessable(!hLevel1Black[14-i][14-j].isAccessable());				
			}
		}
		
		//Level 1 Tür, Key, Platform 
		hLevel1Black[zDoorBlack[0][0]][zDoorBlack[0][1]].setDoor(true);
		hLevel1Black[zKey1Black[0][0]][zKey1Black[0][0]].setKey(true);
		for(int i=0;i<zPlatform1Black[0][0].length;i++)
		{hLevel1Black[zPlatform1Black[0][0][i][0]][zPlatform1Black[0][0][i][1]].setPlatform(true);}
		
		hLevel1White[14-zDoorBlack[0][0]][14-zDoorBlack[0][1]].setDoor(true);
		hLevel1White[14-zKey1Black[0][0]][14-zKey1Black[0][1]].setKey(true);
		
		for(int i=0;i<zPlatform1Black[0][0].length;i++)
		{hLevel1White[14-zPlatform1Black[0][0][i][0]][14-zPlatform1Black[0][0][i][1]].setPlatform(true);}
		
		
		
		//Level 2Black
		for(int i=0;i<3;i++){for(int j=0;j<2;j++){
			hLevel2Black[i][j].setAcessable(true);
		}}
		for(int j=2;j<5;j++){
			hLevel2Black[0][j].setAcessable(true);
		}
		for(int i=4;i<6;i++){for(int j=0;j<3;j++){
			hLevel2Black[i][j].setAcessable(true);
		}}
		for(int i=6;i<13;i++){
			hLevel2Black[i][0].setAcessable(true);
		}
		for(int i=5;i<10;i++){for(int j=3;j<5;j++){
			hLevel2Black[i][j].setAcessable(true);
		}}
		for(int i=8;i<10;i++){for(int j=5;j<7;j++){
			hLevel2Black[i][j].setAcessable(true);
		}}
		for(int i=8;i<11;i++){
			hLevel2Black[i][6].setAcessable(true);
		}
		for(int i=13;i<15;i++){for(int j=0;j<7;j++){
			hLevel2Black[i][j].setAcessable(true);
		}}
		for(int j=2;j<4;j++){
			hLevel2Black[12][j].setAcessable(true);
		}
		for(int i=4;i<7;i++){for(int j=6;j<8;j++){
			hLevel2Black[i][j].setAcessable(true);
		}}
		for(int j=8;j<10;j++){
			hLevel2Black[6][j].setAcessable(true);
		}
		for(int i=6;i<11;i++){for(int j=10;j<13;j++){
			hLevel2Black[i][j].setAcessable(true);
		}}
		for(int i=8;i<10;i++){for(int j=13;j<15;j++){
			hLevel2Black[i][j].setAcessable(true);
		}}
		for(int i=13;i<15;i++){for(int j=10;j<12;j++){
			hLevel2Black[i][j].setAcessable(true);
		}}
		hLevel2Black[0][10].setAcessable(true);
		for(int i=1;i<4;i++){for(int j=10;j<12;j++){
			hLevel2Black[i][j].setAcessable(true);
		}}
		hLevel2Black[1][12].setAcessable(true);
		for(int i=0;i<2;i++){
			hLevel2Black[i][13].setAcessable(true);
		}
		
		
		//Level 2 White
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<15;j++)
			{
				hLevel2White[i][j].setAcessable(!hLevel2Black[14-i][14-j].isAccessable());
			}
		}
		
		//Level 2 Tür, Key, Platform, Spikes 
		hLevel2Black[zDoorBlack[1][0]][zDoorBlack[1][1]].setDoor(true);
		hLevel2Black[zKey1Black[1][0]][zKey1Black[1][1]].setKey(true);
		hLevel2Black[zKey2Black[1][0]][zKey2Black[1][1]].setKey(true);
		hLevel2Black[zKey3Black[1][0]][zKey3Black[1][1]].setKey(true);
		for(int i=0;i<zPlatform1Black[1][0].length;i++)
		{hLevel2Black[zPlatform1Black[1][0][i][0]][zPlatform1Black[1][0][i][1]].setPlatform(true);}
		for(int i=0;i<zPlatform2Black[1][0].length;i++)
		{hLevel2Black[zPlatform2Black[1][0][i][0]][zPlatform2Black[1][0][i][1]].setPlatform(true);}
		for(int i=0;i<zPlatform3Black[1][0].length;i++)
		{hLevel2Black[zPlatform3Black[1][0][i][0]][zPlatform3Black[1][0][i][1]].setPlatform(true);}
		for(int i=0;i<zSpikesBlack[1].length;i++)
		{hLevel2Black[zSpikesBlack[1][i][0]][zSpikesBlack[1][i][1]].setSpike(true);}
		
		hLevel2White[14-zDoorBlack[1][0]][14-zDoorBlack[1][1]].setDoor(true);
		hLevel2White[14-zKey1Black[1][0]][14-zKey1Black[1][1]].setKey(true);
		hLevel2White[14-zKey2Black[1][0]][14-zKey2Black[1][1]].setKey(true);
		hLevel2White[14-zKey3Black[1][0]][14-zKey3Black[1][1]].setKey(true);
		for(int i=0;i<zPlatform1Black[1][0].length;i++)
		{hLevel2White[14-zPlatform1Black[1][0][i][0]][14-zPlatform1Black[1][0][i][1]].setPlatform(true);}
		for(int i=0;i<zPlatform2Black[1][0].length;i++)
		{hLevel2White[14-zPlatform2Black[1][0][i][0]][14-zPlatform2Black[1][0][i][1]].setPlatform(true);}
		for(int i=0;i<zPlatform3Black[1][0].length;i++)
		{hLevel2White[14-zPlatform3Black[1][0][i][0]][14-zPlatform3Black[1][0][i][1]].setPlatform(true);}
		for(int i=0;i<zSpikesWhite[1].length;i++)
		{hLevel2White[zSpikesWhite[1][i][0]][zSpikesWhite[1][i][1]].setSpike(true);}
		
	}
	
	
	/**<dd>
	 * <h3><i> getLevel </i></h3>
	 * <p>
	 * <code>{@code public getLevel({@link integer} pLevel, {@link integer} pVersion)}</code>
	 * </p>
	 * returns the right two-dimensional Array for the field, by given level and version
	 * @param pLevel
	 * @param pVersion
	 * @return Feld[][] - [x][y]
	 */
	public Feld[][] getLevel(int pLevel, int pVersion)
	{
		if(pLevel==1&&pVersion==0)
		{return hLevel1Black;}
		else if(pLevel==1&&pVersion==1)
		{return hLevel1White;}
		
		else if(pLevel==2&&pVersion==0)
		{return hLevel2Black;}
		else if(pLevel==2&&pVersion==1)
		{return hLevel2White;}
		
		return null;
	}
	
	/**<dd>
	 * <h3><i> getBackground </i></h3>
	 * <p>
	 * <code>{@code public getBackground({@link integer} pLevel} isAcessable)}</code>
	 * </p>
	 * returns the background image for the given level
	 * @param pLevel
	 * @return BufferedImage
	 */
	public BufferedImage[] getBackground(int pLevel)
	{
		if(pLevel==1)
		{return zLevel1;}
		else if(pLevel==2)
		{return zLevel2;}
		
		return null;
	}
	
	/**<dd>
	 * <h3><i> getPlayerPosition </i></h3>
	 * <p>
	 * <code>{@code public getPlayerPosition({@link integer} pLevel)}</code>
	 * </p>
	 * returns the starting position of the player for the given level
	 * @param pLevel
	 * @return int[] - [0]=x; [1]=y
	 */
	public int[] getPlayerPosition(int pLevel)
	{
		return zPlayerPosition[pLevel-1];
	}
	/**<dd>
	 * <h3><i> getPlatform </i></h3>
	 * <p>
	 * <code>{@code public getPlatform({@link integer} pLevel, {@link integer} pVersion,{@link integer} pLage,{@link integer} pNummer)}</code>
	 * </p>
	 * returns the platform for the given level, version, orientation and number
	 * @param pLevel
	 * @param pVersion
	 * @param pLage
	 * @param pNummer
	 * @return int[][] - [Platformteil][X/Y]
	 **/
	public int[][] getPlatform(int pLevel,int pVersion,int pLage,int pNummer)
	{
		if(pNummer==0)
		{
			if(pVersion==0)
			{return zPlatform1Black[pLevel-1][pLage];}
			else if(pVersion==1)
			{return zPlatform1White[pLevel-1][pLage];}
		}
		else if(pNummer==1)
		{
			if(pVersion==0)
			{return zPlatform2Black[pLevel-1][pLage];}
			else if(pVersion==1)
			{return zPlatform2White[pLevel-1][pLage];}
		}
		else if(pNummer==2)
		{
			if(pVersion==0)
			{return zPlatform3Black[pLevel-1][pLage];}
			else if(pVersion==1)
			{return zPlatform3White[pLevel-1][pLage];}
		}
		return null;
	}

	/**<dd>
	 * <h3><i> getDoor </i></h3>
	 * <p>
	 * <code>{@code public getDoor({@link integer} pLevel, {@link integer} pVersion)}</code>
	 * </p>
	 * returns the position of the exit for the given level and version
	 * @param pLevel
	 * @param pVersion
	 * @return int[] - [0]=x; [1]=y
	 **/
	public int[] getDoor(int pLevel,int pVersion)
	{
		if(pVersion==0)
		{return zDoorBlack[pLevel-1];}
		else if(pVersion==1)
		{return zDoorWhite[pLevel-1];}
		return null;
	}
	/**<dd>
	 * <h3><i> getKey1 </i></h3>
	 * <p>
	 * <code>{@code public getKey1({@link integer} pLevel, {@link integer} pVersion)}</code>
	 * </p>
	 * returns the position of the first key of the given level and version
	 * @param pLevel
	 * @param pVersion
	 * @return int[] - [0]=x; [1]=y
	 **/
	public int[] getKey1(int pLevel, int pVersion)
	{
		if(pVersion==0)
		{return zKey1Black[pLevel-1];}
		else if(pVersion==1)
		{return zKey1White[pLevel-1];}
		return null;
	}
	/**<dd>
	 * <h3><i> getKey2 </i></h3>
	 * <p>
	 * <code>{@code public getKey2({@link integer} pLevel, {@link integer} pVersion)}</code>
	 * </p>
	 * returns the position of the second key of the given level and version
	 * @param pLevel
	 * @param pVersion
	 * @return int[] - [0]=x; [1]=y
	 **/
	public int[] getKey2(int pLevel, int pVersion)
	{
		if(pVersion==0)
		{return zKey2Black[pLevel-1];}
		else if(pVersion==1)
		{return zKey2White[pLevel-1];}
		return null;
	}
	/**<dd>
	 * <h3><i> getKey3 </i></h3>
	 * <p>
	 * <code>{@code public getKey3({@link integer} pLevel, {@link integer} pVersion)}</code>
	 * </p>
	 * returns the position of the third key of the given level and version
	 * @param pLevel
	 * @param pVersion
	 * @return int[] - [0]=x; [1]=y
	 **/
	public int[] getKey3(int pLevel, int pVersion)
	{
		if(pVersion==0)
		{return zKey3Black[pLevel-1];}
		else if(pVersion==1)
		{return zKey3White[pLevel-1];}
		return null;
	}
	
	/**<dd>
	 * <h3><i> getKeyNumbers </i></h3>
	 * <p>
	 * <code>{@code public getKeyNumbers({@link integer} pLevel)}</code>
	 * </p>
	 * returns the total number of key in the given level
	 * @param pLevel
	 * @return
	 */
	public int getKeyNumbers(int pLevel)
	{
		return zKeyNumbers[pLevel-1];
	}
	
	/**<dd>
	 * <h3><i> getSpikes </i></h3>
	 * <p>
	 * <code>{@code public getSpikes({@link integer} pLevel, {@link integer} pVersion)}</code>
	 * </p>
	 * returns the position of the spikes for the given level and version
	 * @param pLevel
	 * @param pVersion
	 * @return int[][] - [partnumber][X/Y]
	 **/
	public int[][] getSpikes(int pLevel,int pVersion)
	{
		if(pVersion==0)
		{return zSpikesBlack[pLevel-1];}
		else if(pVersion==1)
		{return zSpikesWhite[pLevel-1];}
		return null;
	}
	
	/**<dd>
	 * <h3><i> setPlatform </i></h3>
	 * <p>
	 * <code>{@code public setPlatform({@link integer} pLevel, {@link integer} pVersion,{@link integer) pNummer}</code>
	 * </p>
	 * sets the Platform for the given level, orientation and number
	 * @param pLevel
	 * @param pLage
	 * @param pNummer
	 */
	public void setPlatform(int pLevel, int pLage,int pNummer)
	{
		if(pLevel==1)
		{
			if(pNummer==0)
			{
				int pInvLag=(pLage+1)%2;
				for(int i=0;i<zPlatform1Black[pLevel-1][pInvLag].length;i++)
				{hLevel1Black[zPlatform1Black[pLevel-1][pInvLag][i][0]][zPlatform1Black[pLevel-1][pInvLag][i][1]].setPlatform(false);}
				for(int i=0;i<zPlatform1Black[pLevel-1][pInvLag].length;i++)
				{hLevel1White[14-zPlatform1Black[pLevel-1][pInvLag][i][0]][14-zPlatform1Black[pLevel-1][pInvLag][i][1]].setPlatform(false);}
				
				for(int i=0;i<zPlatform1Black[pLevel-1][pLage].length;i++)
				{hLevel1Black[zPlatform1Black[pLevel-1][pLage][i][0]][zPlatform1Black[pLevel-1][pLage][i][1]].setPlatform(true);}
				for(int i=0;i<zPlatform1Black[pLevel-1][pLage].length;i++)
				{hLevel1White[14-zPlatform1Black[pLevel-1][pLage][i][0]][14-zPlatform1Black[pLevel-1][pLage][i][1]].setPlatform(true);}
			}
			else if(pNummer==1)
			{
				int pInvLag=(pLage+1)%2;
				for(int i=0;i<zPlatform2Black[pLevel-1][pInvLag].length;i++)
				{hLevel1Black[zPlatform2Black[pLevel-1][pInvLag][i][0]][zPlatform2Black[pLevel-1][pInvLag][i][1]].setPlatform(false);}
				for(int i=0;i<zPlatform2Black[pLevel-1][pInvLag].length;i++)
				{hLevel1White[14-zPlatform2Black[pLevel-1][pInvLag][i][0]][14-zPlatform2Black[pLevel-1][pInvLag][i][1]].setPlatform(false);}
				
				for(int i=0;i<zPlatform2Black[pLevel-1][pLage].length;i++)
				{hLevel1Black[zPlatform2Black[pLevel-1][pLage][i][0]][zPlatform2Black[pLevel-1][pLage][i][1]].setPlatform(true);}
				for(int i=0;i<zPlatform2Black[pLevel-1][pLage].length;i++)
				{hLevel1White[14-zPlatform2Black[pLevel-1][pLage][i][0]][14-zPlatform2Black[pLevel-1][pLage][i][1]].setPlatform(true);}
			}
			else if(pNummer==2)
			{
				int pInvLag=(pLage+1)%2;
				for(int i=0;i<zPlatform3Black[pLevel-1][pInvLag].length;i++)
				{hLevel1Black[zPlatform3Black[pLevel-1][pInvLag][i][0]][zPlatform3Black[pLevel-1][pInvLag][i][1]].setPlatform(false);}
				for(int i=0;i<zPlatform3Black[pLevel-1][pInvLag].length;i++)
				{hLevel1White[14-zPlatform3Black[pLevel-1][pInvLag][i][0]][14-zPlatform3Black[pLevel-1][pInvLag][i][1]].setPlatform(false);}
				
				for(int i=0;i<zPlatform3Black[pLevel-1][pLage].length;i++)
				{hLevel1Black[zPlatform3Black[pLevel-1][pLage][i][0]][zPlatform3Black[pLevel-1][pLage][i][1]].setPlatform(true);}
				for(int i=0;i<zPlatform3Black[pLevel-1][pLage].length;i++)
				{hLevel1White[14-zPlatform2Black[pLevel-1][pLage][i][0]][14-zPlatform3Black[pLevel-1][pLage][i][1]].setPlatform(true);}
			}
		}
		else if(pLevel==2)
		{
			if(pNummer==0)
			{
				int pInvLag=(pLage+1)%2;
				for(int i=0;i<zPlatform1Black[pLevel-1][pInvLag].length;i++)
				{hLevel2Black[zPlatform1Black[pLevel-1][pInvLag][i][0]][zPlatform1Black[pLevel-1][pInvLag][i][1]].setPlatform(false);}
				for(int i=0;i<zPlatform1Black[pLevel-1][pInvLag].length;i++)
				{hLevel2White[14-zPlatform1Black[pLevel-1][pInvLag][i][0]][14-zPlatform1Black[pLevel-1][pInvLag][i][1]].setPlatform(false);}
				
				for(int i=0;i<zPlatform1Black[pLevel-1][pLage].length;i++)
				{hLevel2Black[zPlatform1Black[pLevel-1][pLage][i][0]][zPlatform1Black[pLevel-1][pLage][i][1]].setPlatform(true);}
				for(int i=0;i<zPlatform1Black[pLevel-1][pLage].length;i++)
				{hLevel2White[14-zPlatform1Black[pLevel-1][pLage][i][0]][14-zPlatform1Black[pLevel-1][pLage][i][1]].setPlatform(true);}
			}
			else if(pNummer==1)
			{
				int pInvLag=(pLage+1)%2;
				for(int i=0;i<zPlatform2Black[pLevel-1][pInvLag].length;i++)
				{hLevel2Black[zPlatform2Black[pLevel-1][pInvLag][i][0]][zPlatform2Black[pLevel-1][pInvLag][i][1]].setPlatform(false);}
				for(int i=0;i<zPlatform2Black[pLevel-1][pInvLag].length;i++)
				{hLevel2White[14-zPlatform2Black[pLevel-1][pInvLag][i][0]][14-zPlatform2Black[pLevel-1][pInvLag][i][1]].setPlatform(false);}
				
				for(int i=0;i<zPlatform2Black[pLevel-1][pLage].length;i++)
				{hLevel2Black[zPlatform2Black[pLevel-1][pLage][i][0]][zPlatform2Black[pLevel-1][pLage][i][1]].setPlatform(true);}
				for(int i=0;i<zPlatform2Black[pLevel-1][pLage].length;i++)
				{hLevel2White[14-zPlatform2Black[pLevel-1][pLage][i][0]][14-zPlatform2Black[pLevel-1][pLage][i][1]].setPlatform(true);}
			}
			else if(pNummer==2)
			{
				int pInvLag=(pLage+1)%2;
				for(int i=0;i<zPlatform3Black[pLevel-1][pInvLag].length;i++)
				{hLevel2Black[zPlatform3Black[pLevel-1][pInvLag][i][0]][zPlatform3Black[pLevel-1][pInvLag][i][1]].setPlatform(false);}
				for(int i=0;i<zPlatform3Black[pLevel-1][pInvLag].length;i++)
				{hLevel2White[14-zPlatform3Black[pLevel-1][pInvLag][i][0]][14-zPlatform3Black[pLevel-1][pInvLag][i][1]].setPlatform(false);}
				
				for(int i=0;i<zPlatform3Black[pLevel-1][pLage].length;i++)
				{hLevel2Black[zPlatform3Black[pLevel-1][pLage][i][0]][zPlatform3Black[pLevel-1][pLage][i][1]].setPlatform(true);}
				for(int i=0;i<zPlatform3Black[pLevel-1][pLage].length;i++)
				{hLevel2White[14-zPlatform3Black[pLevel-1][pLage][i][0]][14-zPlatform3Black[pLevel-1][pLage][i][1]].setPlatform(true);}
			}
		}
	}
	
	/**<dd>
	 * <h3><i> resetKeys </i></h3>
	 * <p>
	 * <code>{@code public resetKeys({@link integer} pLevel)}</code>
	 * </p>
	 * resets the Keys in the given level
	 * @param pLevel
	 */
	public void resetKeys(int pLevel)
	{
		if(pLevel==1)
		{
			hLevel1Black[zKey1Black[0][0]][zKey1Black[0][0]].setKey(true);
			hLevel1White[14-zKey1Black[0][0]][14-zKey1Black[0][1]].setKey(true);
		}
		else if(pLevel==2)
		{
			hLevel2Black[zKey1Black[1][0]][zKey1Black[1][1]].setKey(true);
			hLevel2Black[zKey2Black[1][0]][zKey2Black[1][1]].setKey(true);
			hLevel2Black[zKey3Black[1][0]][zKey3Black[1][1]].setKey(true);
			
			hLevel2White[14-zKey1Black[1][0]][14-zKey1Black[1][1]].setKey(true);
			hLevel2White[14-zKey2Black[1][0]][14-zKey2Black[1][1]].setKey(true);
			hLevel2White[14-zKey3Black[1][0]][14-zKey3Black[1][1]].setKey(true);
		}
	}

}
