/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

public class Main
{
    static float emi_calculator(float p,float r, float t)
    {
        float emi;
     
        r = r / (12 * 100); // one month interest
        t = t * 12; // one month period
        emi = (p * r * (float)Math.pow(1 + r, t))
                / (float)(Math.pow(1 + r, t) - 1);
     
        return (emi);
    }
	public static void main(String[] args) {
		float principal, rate, time, emi;
        principal = 10000;
        rate = 10;
        time = 2;
         
        emi = emi_calculator(principal, rate, time);
         
        System.out.println("Monthly EMI is = " + emi);
	}
}
