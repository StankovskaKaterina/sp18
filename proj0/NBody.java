public class NBody{

public static double readRadius(String filename){
		In in = new In(filename);
		in.readInt();
		return in.readDouble();
	}

public static Planet[] readPlanets(String filename){
		In in = new In(filename);
		Planet[] array = new Planet[5];

		/* Skip the first two lines */
		in.readLine();
		in.readLine();

		for(int i=0; i<5; i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			array[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}
		return array;
	}


	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		//Read in the planets and the universe radius from the file described by filename
		double r = readRadius(filename);
		Planet[] planets =readPlanets(filename);
		StdDraw.setScale(-r,r);
		//StdDraw.clear();
		//setting the background
		StdDraw.picture(0, 0,"images/starfield.jpg");
		// drawing the planets
		int total = 0;
    	for(Planet planet : planets) {
      		planet.draw();
      		total++;
    	}
		//Creating an Animation
    	//enableDoubleBuffering();
    	for (double time=0; time<=T; time+=dt) {
			double[] xForces = new double[total];
			double[] yForces = new double[total];
			for (int i=0; i<total; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int i=0; i<total; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Planet planet : planets) {
				planet.draw();
			}
			StdDraw.show(10);

		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", r);
		for (int i = 0; i < planets.length; i++) {
    			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
}