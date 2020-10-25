public class Planet{ 

        public double xxPos;
        public double yyPos;
        public double xxVel;
        public double yyVel;
        public double mass;
        public String imgFileName;
        static double G= 6.67e-11;
public Planet(double xP, double yP, double xV, double yV, double m, String img){
    	xxPos= xP;
    	yyPos=yP;
    	xxVel =xV;
    	yyVel=yV;
    	mass = m;
    	imgFileName = img.toLowerCase();
}
public Planet(Planet planet){
    	xxPos=planet.xxPos;
    	yyPos=planet.yyPos;
    	xxVel=planet.xxVel;
    	yyVel=planet.yyVel;
    	mass=planet.mass;
    	imgFileName=planet.imgFileName;
        }
//Take in a single Planet and should return a double equal to the distance 
//between the supplied planet and the planet that is doing the calculation r^2=(dx)^2+(dy)^2
 public double calcDistance (Planet planet){
   	 	return Math.sqrt(((planet.xxPos - this.xxPos) * (planet.xxPos - this.xxPos)) + 
   	 		((planet.yyPos - this.yyPos) * (planet.yyPos - this.yyPos)));
 }
 //Takes in a planet, and returns a double describing the total force exerted 
 //on this planet by the given planet. You should be calling the calcDistance method in this method.
 public double calcForceExertedBy(Planet planet){
 	return ((G*planet.mass * this.mass)/(this.calcDistance(planet)*this.calcDistance(planet)));
 	
 }
// these two methods describe the force exerted in the X and Y directions, respectively 
// F*(xPos1-xPos2)/r == F*dx/r 
 public double calcForceExertedByX(Planet planet){
		return ((this.calcForceExertedBy(planet)* (planet.xxPos- this.xxPos))/ this.calcDistance(planet));
 }

// F*(yPos1-yPos2)/r == F*dy/r 
 public double calcForceExertedByY(Planet planet){
		return ((this.calcForceExertedBy(planet)* (planet.yyPos- this.yyPos))/ this.calcDistance(planet));
 }
//These methods take in an array of Planets and calculate the net X and net Y force exerted 
// by all planets in that array upon the current Planet

 public double calcNetForceExertedByX (Planet [] Planets){
	double total = 0.0;
	for (Planet planet : Planets){

		if (planet.equals(this)){
			total+=0;
		}
		else{
			total+= this.calcForceExertedByX(planet);
		} 
	}
return total;
 }

 public double calcNetForceExertedByY (Planet [] Planets){
 double total = 0.0;
	for (Planet planet : Planets){

		if (planet.equals(this)){
			total+=0;
		}
		else{
			total+= this.calcForceExertedByY(planet);
		} 
	}
 return total;

 }
// This method determines how much the forces exerted on the planetwill cause that planet 
 //to accelerate, and the resulting change in the planet’s velocity and position in a small period of time dt.
public void update(double dt, double fX, double fY){
double accX = fX / this.mass;
double accY = fY / this.mass;
this.xxVel += (dt* accX);
this.yyVel += (dt * accY);
this.xxPos += (dt* xxVel);
this.yyPos += (dt* yyVel);  
}

public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
}

}

