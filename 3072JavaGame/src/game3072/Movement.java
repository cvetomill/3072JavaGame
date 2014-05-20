package game3072;

public class Movement extends game3072.Engine {

	//Logic implementing the upward movement when the Up arrow is pressed
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
				    		 Engine.scoreCounter+=map[y][x]*2;
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
	     if(move==true) // If the movement is allowed, pass the next steps to the GetRandomPosition() method from the Engine class.
	     {
	    	 GetRandomPosition();
	     }
	}
	
	//Logic implementing the downward movement when the Down arrow is pressed
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
				    		 Engine.scoreCounter+=map[y][x]*2;
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
	
	//Logic implementing left movement when the Left arrow is pressed
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
				    		 Engine.scoreCounter+=map[y][x]*2;
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
	
	//Logic implementing right movement when the Right arrow is pressed
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
				    		 Engine.scoreCounter+=map[y][x]*2;
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