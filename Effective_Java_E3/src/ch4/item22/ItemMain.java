package ch4.item22;

import static ch4.item22.PhysicalConstantsC.*;
//Subject : Use interfaces only to define types
//Use of static import to avoid qualifying constants
public class ItemMain {
    double atoms(double mols){
        return AVOGADROS_NUMBER * mols;
    }
}