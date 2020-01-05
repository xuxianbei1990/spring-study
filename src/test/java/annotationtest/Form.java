package annotationtest;
import annotation.diy.DateString;

public class Form {  
    @DateString  
    private String current;  
  
    /** 
     * Getter method for property <tt>current</tt>. 
     * 
     * @return property value of current 
     */  
    public String getCurrent() {  
        return current;  
    }  
  
    /** 
     * Setter method for property <tt>current</tt>. 
     * 
     * @param current value to be assigned to property current 
     */  
    public void setCurrent(String current) {  
        this.current = current;  
    }  
  
}  