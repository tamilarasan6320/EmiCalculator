public class Main
{
    static float principal_calculator(float emi,float r, float t)
    {
        float p;
     
        r = r / (12 * 100); // one month interest
        t = t * 12; // one month period
        p = ((float)(Math.pow(1 + r, t) - 1) * emi) / (r * (float)Math.pow(1 + r, t));
        
                
     
        return (p);
    }
	public static void main(String[] args) {
		float principal, rate, time, emi;
        emi = 2000;
        rate = 10;
        time = 2;
         
        principal = principal_calculator(emi, rate, time);
         
        System.out.println("Principle is = " + principal);
	}
}