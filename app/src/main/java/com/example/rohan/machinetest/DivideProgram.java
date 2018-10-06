package com.example.rohan.machinetest;

import java.util.Scanner;

/**
 * Created by rohan on 10/4/2018.
 */
public class DivideProgram {
        public static void main(String[] args) {
            int res,rem=0,num,div,qu=0;

            Scanner s= new Scanner(System.in);
            System.out.println("Enter number:");
            num=s.nextInt();
            System.out.println("Enter divisor:");
            div=s.nextInt();

            for(int i=1;i<num;i++){
                res=div*i;
                if(res>num) {
                    res=res-div;
                    qu=i-1;
                    rem=num-res;break;
                }
            }
            System.out.println("Quotient:" +qu);
            System.out.println("Remainder:" +rem);


        }
    }

