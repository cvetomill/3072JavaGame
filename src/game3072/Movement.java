package game3072;

public class Movement extends game3072.Engine {

	public static void moveUp() {
	     boolean move=false;
	     for (int y=0;y<mapLimits;y++) {
	    	 for (int x=1;x<mapLimits;x++) {
		    	 if (map[y][x]!=0)
		    	 {
		    		 for (int X=x-1;X>=0;X--) {
				    	 if(map[y][X]==0)
				    	 {
				    		 move=true;
				    		 map[y][X]=map[y][x];
				    		 map[y][x]=0;
				    	     x=X;
				    	 } else if (map[y][X]==map[y][x]) {
				    		 move=true;
				    		 map[y][X]=(short) (2*map[y][x]);
				    		 map[y][x]=0;
				    		 break;
				    	 } else {
				    		 break;
				    	 }
				     }
		    	 }
		     } 
	     }
	     if(move==true)
	     {
	    	 GetRandomPosition();
	     }
	}
	
	public static void moveLeft() {
		boolean move=false;
	     for (int y=1;y<mapLimits;y++){
	    	 for (int x=0; x<mapLimits;x++){
		    	 if (map[y][x]!=0)
		    	 {
		    		 for (int Y=y-1;Y>=0;Y--){
				    	 if (map[Y][x]==0)
				    	 {
				    		 move=true;
				    		 map[Y][x]=map[y][x];
				    		 map[y][x]=0;
				    		 y=Y;
				    	 } else if (map[Y][x]==map[y][x]){
				    		 move=true;
				    		 map[Y][x]=(short) (2*map[y][x]);
				    		 map[y][x]=0;
				    		 break;
				    	 } else {
				    		 break;
				    	 }
				     }
		    	 }
		     } 
	     }
	     if (move==true)
	     {
	    	 GetRandomPosition();
	     }
	}	
	
	public static void moveDown(){
	     boolean move=false;
	     for (int y=0;y<mapLimits;y++){
	    	 for (int x=mapLimits - 2; x>=0;x--){
		    	 if (map[y][x]!=0)
		    	 {
		    		 for (int X=x+1;X< mapLimits;X++){
				    	 if (map[y][X]==0)
				    	 {
				    		 move=true;
				    		 map[y][X]=map[y][x];
				    		 map[y][x]=0;
				    	     x=X;
				    	 } else if (map[y][X]==map[y][x]){
				    		 move=true;
				    		 map[y][X]=(short) (2*map[y][x]);
				    		 map[y][x]=0;
				    		 break;
				    	 } else {
				    		 break;
				    	 }
				     }
		    	 }
		     } 
	     }
	     if (move==true)
	     {
	    	 GetRandomPosition();
	     }
	}
	
	public static void moveRight() {
		boolean move=false;
	     for (int y=mapLimits - 2;y>=0;y--){
	    	 for (int x=0; x<mapLimits;x++){
		    	 if (map[y][x]!=0)
		    	 {
		    		 for (int Y=y+1;Y< mapLimits;Y++){
				    	 if (map[Y][x]==0)
				    	 {
				    		 move=true;
				    		 map[Y][x]=map[y][x];
				    		 map[y][x]=0;
				    	     y=Y;
				    	 } else if (map[Y][x]==map[y][x]){
				    		 move=true;
				    		 map[Y][x]=(short) (2*map[y][x]);
				    		 map[y][x]=0;
				    		 break;
				    	 } else {
				    		 break;
				    	 }
				     }
		    	 }
		     } 
	     }
	     if (move==true)
	     {
	    	 GetRandomPosition();
	     }
	}
	
}