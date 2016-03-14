/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javax.persistence.Persistence;

/**
 *
 * @author pagh
 */
public class Tester
{
    public static void main(String[] args)
    {
        Persistence.generateSchema("CA2-Object_Relational_Mapping_Rest_Test_Ajax_JqueryPU", null);
    }
 
}
