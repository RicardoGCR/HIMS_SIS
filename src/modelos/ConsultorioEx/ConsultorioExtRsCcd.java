/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;

import javax.xml.bind.annotation.XmlRootElement;
import servicios.Conexion;

public class ConsultorioExtRsCcd implements Serializable {
    private static final long serialVersionUID = 1L;
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int rsCcd;
    private String rn1Fecha;
    private String rn1Cie10;
    private String rn1Fua;
    private String rn2Fecha;
    private String rn2Cie10;
    private String rn2Fua;
    private String rn3Fecha;
    private String rn3Cie10;
    private String rn3Fua;
    private String rn4Fecha;
    private String rn4Cie10;
    private String rn4Fua;
    private String m1Fecha;
    private String m1Cie10;
    private String m1Fua;
    private String m2Fecha;
    private String m2Cie10;
    private String m2Fua;
    private String m3Fecha;
    private String m3Cie10;
    private String m3Fua;
    private String m4Fecha;
    private String m4Cie10;
    private String m4Fua;
    private String m5Fecha;
    private String m5Cie10;
    private String m5Fua;
    private String m6Fecha;
    private String m6Cie10;
    private String m6Fua;
    private String m7Fecha;
    private String m7Cie10;
    private String m7Fua;
    private String m8Fecha;
    private String m8Cie10;
    private String m8Fua;
    private String m9Fecha;
    private String m9Cie10;
    private String m9Fua;
    private String m10Fecha;
    private String m10Cie10;
    private String m10Fua;
    private String m11Fecha;
    private String m11Cie10;
    private String m11Fua;
    private String m11Fecha1;
    private String m11Cie101;
    private String m11Fua1;
    private String m12Fecha;
    private String m12Cie10;
    private String m12Fua;
    private String m13Fecha;
    private String m13Cie10;
    private String m13Fua;
    private String m14Fecha;
    private String m14Cie10;
    private String m14Fua;
    private String m15Fecha;
    private String m15Cie10;
    private String m15Fua;
    private String m16Fecha;
    private String m16Cie10;
    private String m16Fua;
    private String m21Fecha;
    private String m21Cie10;
    private String m21Fua;
    private String m22Fecha;
    private String m22Cie10;
    private String m22Fua;
    private String m23Fecha;
    private String m23Cie10;
    private String m23Fua;
    private String m24Fecha;
    private String m24Cie10;
    private String m24Fua;
    private String m31Fecha;
    private String m31Cie10;
    private String m31Fua;
    private String m32Fecha;
    private String m32Cie10;
    private String m32Fua;
    private String m33Fecha;
    private String m33Cie10;
    private String m33Fua;
    private String m34Fecha;
    private String m34Cie10;
    private String m34Fua;
    private String m41Fecha;
    private String m41Cie10;
    private String m41Fua;
    private String m42Fecha;
    private String m42Cie10;
    private String m42Fua;
    private String m43Fecha;
    private String m43Cie10;
    private String m43Fua;
    private String m44Fecha;
    private String m44Cie10;
    private String m44Fua;
    private String m5Fecha1;
    private String m5Cie101;
    private String m5Fua1;
    private String m6Fecha1;
    private String m6Cie101;
    private String m6Fua1;
    private String m7Fecha1;
    private String m7Cie101;
    private String m7Fua1;
    private String m8Fecha1;
    private String m8Cie101;
    private String m8Fua1;
    private String m9Fecha1;
    private String m9Cie101;
    private String m9Fua1;
    private String m10Fecha1;
    private String m10Cie101;
    private String m10Fua1;
    private String m11Fecha2;
    private String m11Cie102;
    private String m11Fua2;

    public ConsultorioExtRsCcd() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsCcd(int rsCcd) {
        this.rsCcd = rsCcd;
    }

    public int getRsCcd() {
        return rsCcd;
    }

    public void setRsCcd(int rsCcd) {
        this.rsCcd = rsCcd;
    }

    public String getRn1Fecha() {
        return rn1Fecha;
    }

    public void setRn1Fecha(String rn1Fecha) {
        this.rn1Fecha = rn1Fecha;
    }

    public String getRn1Cie10() {
        return rn1Cie10;
    }

    public void setRn1Cie10(String rn1Cie10) {
        this.rn1Cie10 = rn1Cie10;
    }

    public String getRn1Fua() {
        return rn1Fua;
    }

    public void setRn1Fua(String rn1Fua) {
        this.rn1Fua = rn1Fua;
    }

    public String getRn2Fecha() {
        return rn2Fecha;
    }

    public void setRn2Fecha(String rn2Fecha) {
        this.rn2Fecha = rn2Fecha;
    }

    public String getRn2Cie10() {
        return rn2Cie10;
    }

    public void setRn2Cie10(String rn2Cie10) {
        this.rn2Cie10 = rn2Cie10;
    }

    public String getRn2Fua() {
        return rn2Fua;
    }

    public void setRn2Fua(String rn2Fua) {
        this.rn2Fua = rn2Fua;
    }

    public String getRn3Fecha() {
        return rn3Fecha;
    }

    public void setRn3Fecha(String rn3Fecha) {
        this.rn3Fecha = rn3Fecha;
    }

    public String getRn3Cie10() {
        return rn3Cie10;
    }

    public void setRn3Cie10(String rn3Cie10) {
        this.rn3Cie10 = rn3Cie10;
    }

    public String getRn3Fua() {
        return rn3Fua;
    }

    public void setRn3Fua(String rn3Fua) {
        this.rn3Fua = rn3Fua;
    }

    public String getRn4Fecha() {
        return rn4Fecha;
    }

    public void setRn4Fecha(String rn4Fecha) {
        this.rn4Fecha = rn4Fecha;
    }

    public String getRn4Cie10() {
        return rn4Cie10;
    }

    public void setRn4Cie10(String rn4Cie10) {
        this.rn4Cie10 = rn4Cie10;
    }

    public String getRn4Fua() {
        return rn4Fua;
    }

    public void setRn4Fua(String rn4Fua) {
        this.rn4Fua = rn4Fua;
    }

    public String getM1Fecha() {
        return m1Fecha;
    }

    public void setM1Fecha(String m1Fecha) {
        this.m1Fecha = m1Fecha;
    }

    public String getM1Cie10() {
        return m1Cie10;
    }

    public void setM1Cie10(String m1Cie10) {
        this.m1Cie10 = m1Cie10;
    }

    public String getM1Fua() {
        return m1Fua;
    }

    public void setM1Fua(String m1Fua) {
        this.m1Fua = m1Fua;
    }

    public String getM2Fecha() {
        return m2Fecha;
    }

    public void setM2Fecha(String m2Fecha) {
        this.m2Fecha = m2Fecha;
    }

    public String getM2Cie10() {
        return m2Cie10;
    }

    public void setM2Cie10(String m2Cie10) {
        this.m2Cie10 = m2Cie10;
    }

    public String getM2Fua() {
        return m2Fua;
    }

    public void setM2Fua(String m2Fua) {
        this.m2Fua = m2Fua;
    }

    public String getM3Fecha() {
        return m3Fecha;
    }

    public void setM3Fecha(String m3Fecha) {
        this.m3Fecha = m3Fecha;
    }

    public String getM3Cie10() {
        return m3Cie10;
    }

    public void setM3Cie10(String m3Cie10) {
        this.m3Cie10 = m3Cie10;
    }

    public String getM3Fua() {
        return m3Fua;
    }

    public void setM3Fua(String m3Fua) {
        this.m3Fua = m3Fua;
    }

    public String getM4Fecha() {
        return m4Fecha;
    }

    public void setM4Fecha(String m4Fecha) {
        this.m4Fecha = m4Fecha;
    }

    public String getM4Cie10() {
        return m4Cie10;
    }

    public void setM4Cie10(String m4Cie10) {
        this.m4Cie10 = m4Cie10;
    }

    public String getM4Fua() {
        return m4Fua;
    }

    public void setM4Fua(String m4Fua) {
        this.m4Fua = m4Fua;
    }

    public String getM5Fecha() {
        return m5Fecha;
    }

    public void setM5Fecha(String m5Fecha) {
        this.m5Fecha = m5Fecha;
    }

    public String getM5Cie10() {
        return m5Cie10;
    }

    public void setM5Cie10(String m5Cie10) {
        this.m5Cie10 = m5Cie10;
    }

    public String getM5Fua() {
        return m5Fua;
    }

    public void setM5Fua(String m5Fua) {
        this.m5Fua = m5Fua;
    }

    public String getM6Fecha() {
        return m6Fecha;
    }

    public void setM6Fecha(String m6Fecha) {
        this.m6Fecha = m6Fecha;
    }

    public String getM6Cie10() {
        return m6Cie10;
    }

    public void setM6Cie10(String m6Cie10) {
        this.m6Cie10 = m6Cie10;
    }

    public String getM6Fua() {
        return m6Fua;
    }

    public void setM6Fua(String m6Fua) {
        this.m6Fua = m6Fua;
    }

    public String getM7Fecha() {
        return m7Fecha;
    }

    public void setM7Fecha(String m7Fecha) {
        this.m7Fecha = m7Fecha;
    }

    public String getM7Cie10() {
        return m7Cie10;
    }

    public void setM7Cie10(String m7Cie10) {
        this.m7Cie10 = m7Cie10;
    }

    public String getM7Fua() {
        return m7Fua;
    }

    public void setM7Fua(String m7Fua) {
        this.m7Fua = m7Fua;
    }

    public String getM8Fecha() {
        return m8Fecha;
    }

    public void setM8Fecha(String m8Fecha) {
        this.m8Fecha = m8Fecha;
    }

    public String getM8Cie10() {
        return m8Cie10;
    }

    public void setM8Cie10(String m8Cie10) {
        this.m8Cie10 = m8Cie10;
    }

    public String getM8Fua() {
        return m8Fua;
    }

    public void setM8Fua(String m8Fua) {
        this.m8Fua = m8Fua;
    }

    public String getM9Fecha() {
        return m9Fecha;
    }

    public void setM9Fecha(String m9Fecha) {
        this.m9Fecha = m9Fecha;
    }

    public String getM9Cie10() {
        return m9Cie10;
    }

    public void setM9Cie10(String m9Cie10) {
        this.m9Cie10 = m9Cie10;
    }

    public String getM9Fua() {
        return m9Fua;
    }

    public void setM9Fua(String m9Fua) {
        this.m9Fua = m9Fua;
    }

    public String getM10Fecha() {
        return m10Fecha;
    }

    public void setM10Fecha(String m10Fecha) {
        this.m10Fecha = m10Fecha;
    }

    public String getM10Cie10() {
        return m10Cie10;
    }

    public void setM10Cie10(String m10Cie10) {
        this.m10Cie10 = m10Cie10;
    }

    public String getM10Fua() {
        return m10Fua;
    }

    public void setM10Fua(String m10Fua) {
        this.m10Fua = m10Fua;
    }

    public String getM11Fecha() {
        return m11Fecha;
    }

    public void setM11Fecha(String m11Fecha) {
        this.m11Fecha = m11Fecha;
    }

    public String getM11Cie10() {
        return m11Cie10;
    }

    public void setM11Cie10(String m11Cie10) {
        this.m11Cie10 = m11Cie10;
    }

    public String getM11Fua() {
        return m11Fua;
    }

    public void setM11Fua(String m11Fua) {
        this.m11Fua = m11Fua;
    }

    public String getM11Fecha1() {
        return m11Fecha1;
    }

    public void setM11Fecha1(String m11Fecha1) {
        this.m11Fecha1 = m11Fecha1;
    }

    public String getM11Cie101() {
        return m11Cie101;
    }

    public void setM11Cie101(String m11Cie101) {
        this.m11Cie101 = m11Cie101;
    }

    public String getM11Fua1() {
        return m11Fua1;
    }

    public void setM11Fua1(String m11Fua1) {
        this.m11Fua1 = m11Fua1;
    }

    public String getM12Fecha() {
        return m12Fecha;
    }

    public void setM12Fecha(String m12Fecha) {
        this.m12Fecha = m12Fecha;
    }

    public String getM12Cie10() {
        return m12Cie10;
    }

    public void setM12Cie10(String m12Cie10) {
        this.m12Cie10 = m12Cie10;
    }

    public String getM12Fua() {
        return m12Fua;
    }

    public void setM12Fua(String m12Fua) {
        this.m12Fua = m12Fua;
    }

    public String getM13Fecha() {
        return m13Fecha;
    }

    public void setM13Fecha(String m13Fecha) {
        this.m13Fecha = m13Fecha;
    }

    public String getM13Cie10() {
        return m13Cie10;
    }

    public void setM13Cie10(String m13Cie10) {
        this.m13Cie10 = m13Cie10;
    }

    public String getM13Fua() {
        return m13Fua;
    }

    public void setM13Fua(String m13Fua) {
        this.m13Fua = m13Fua;
    }

    public String getM14Fecha() {
        return m14Fecha;
    }

    public void setM14Fecha(String m14Fecha) {
        this.m14Fecha = m14Fecha;
    }

    public String getM14Cie10() {
        return m14Cie10;
    }

    public void setM14Cie10(String m14Cie10) {
        this.m14Cie10 = m14Cie10;
    }

    public String getM14Fua() {
        return m14Fua;
    }

    public void setM14Fua(String m14Fua) {
        this.m14Fua = m14Fua;
    }

    public String getM15Fecha() {
        return m15Fecha;
    }

    public void setM15Fecha(String m15Fecha) {
        this.m15Fecha = m15Fecha;
    }

    public String getM15Cie10() {
        return m15Cie10;
    }

    public void setM15Cie10(String m15Cie10) {
        this.m15Cie10 = m15Cie10;
    }

    public String getM15Fua() {
        return m15Fua;
    }

    public void setM15Fua(String m15Fua) {
        this.m15Fua = m15Fua;
    }

    public String getM16Fecha() {
        return m16Fecha;
    }

    public void setM16Fecha(String m16Fecha) {
        this.m16Fecha = m16Fecha;
    }

    public String getM16Cie10() {
        return m16Cie10;
    }

    public void setM16Cie10(String m16Cie10) {
        this.m16Cie10 = m16Cie10;
    }

    public String getM16Fua() {
        return m16Fua;
    }

    public void setM16Fua(String m16Fua) {
        this.m16Fua = m16Fua;
    }

    public String getM21Fecha() {
        return m21Fecha;
    }

    public void setM21Fecha(String m21Fecha) {
        this.m21Fecha = m21Fecha;
    }

    public String getM21Cie10() {
        return m21Cie10;
    }

    public void setM21Cie10(String m21Cie10) {
        this.m21Cie10 = m21Cie10;
    }

    public String getM21Fua() {
        return m21Fua;
    }

    public void setM21Fua(String m21Fua) {
        this.m21Fua = m21Fua;
    }

    public String getM22Fecha() {
        return m22Fecha;
    }

    public void setM22Fecha(String m22Fecha) {
        this.m22Fecha = m22Fecha;
    }

    public String getM22Cie10() {
        return m22Cie10;
    }

    public void setM22Cie10(String m22Cie10) {
        this.m22Cie10 = m22Cie10;
    }

    public String getM22Fua() {
        return m22Fua;
    }

    public void setM22Fua(String m22Fua) {
        this.m22Fua = m22Fua;
    }

    public String getM23Fecha() {
        return m23Fecha;
    }

    public void setM23Fecha(String m23Fecha) {
        this.m23Fecha = m23Fecha;
    }

    public String getM23Cie10() {
        return m23Cie10;
    }

    public void setM23Cie10(String m23Cie10) {
        this.m23Cie10 = m23Cie10;
    }

    public String getM23Fua() {
        return m23Fua;
    }

    public void setM23Fua(String m23Fua) {
        this.m23Fua = m23Fua;
    }

    public String getM24Fecha() {
        return m24Fecha;
    }

    public void setM24Fecha(String m24Fecha) {
        this.m24Fecha = m24Fecha;
    }

    public String getM24Cie10() {
        return m24Cie10;
    }

    public void setM24Cie10(String m24Cie10) {
        this.m24Cie10 = m24Cie10;
    }

    public String getM24Fua() {
        return m24Fua;
    }

    public void setM24Fua(String m24Fua) {
        this.m24Fua = m24Fua;
    }

    public String getM31Fecha() {
        return m31Fecha;
    }

    public void setM31Fecha(String m31Fecha) {
        this.m31Fecha = m31Fecha;
    }

    public String getM31Cie10() {
        return m31Cie10;
    }

    public void setM31Cie10(String m31Cie10) {
        this.m31Cie10 = m31Cie10;
    }

    public String getM31Fua() {
        return m31Fua;
    }

    public void setM31Fua(String m31Fua) {
        this.m31Fua = m31Fua;
    }

    public String getM32Fecha() {
        return m32Fecha;
    }

    public void setM32Fecha(String m32Fecha) {
        this.m32Fecha = m32Fecha;
    }

    public String getM32Cie10() {
        return m32Cie10;
    }

    public void setM32Cie10(String m32Cie10) {
        this.m32Cie10 = m32Cie10;
    }

    public String getM32Fua() {
        return m32Fua;
    }

    public void setM32Fua(String m32Fua) {
        this.m32Fua = m32Fua;
    }

    public String getM33Fecha() {
        return m33Fecha;
    }

    public void setM33Fecha(String m33Fecha) {
        this.m33Fecha = m33Fecha;
    }

    public String getM33Cie10() {
        return m33Cie10;
    }

    public void setM33Cie10(String m33Cie10) {
        this.m33Cie10 = m33Cie10;
    }

    public String getM33Fua() {
        return m33Fua;
    }

    public void setM33Fua(String m33Fua) {
        this.m33Fua = m33Fua;
    }

    public String getM34Fecha() {
        return m34Fecha;
    }

    public void setM34Fecha(String m34Fecha) {
        this.m34Fecha = m34Fecha;
    }

    public String getM34Cie10() {
        return m34Cie10;
    }

    public void setM34Cie10(String m34Cie10) {
        this.m34Cie10 = m34Cie10;
    }

    public String getM34Fua() {
        return m34Fua;
    }

    public void setM34Fua(String m34Fua) {
        this.m34Fua = m34Fua;
    }

    public String getM41Fecha() {
        return m41Fecha;
    }

    public void setM41Fecha(String m41Fecha) {
        this.m41Fecha = m41Fecha;
    }

    public String getM41Cie10() {
        return m41Cie10;
    }

    public void setM41Cie10(String m41Cie10) {
        this.m41Cie10 = m41Cie10;
    }

    public String getM41Fua() {
        return m41Fua;
    }

    public void setM41Fua(String m41Fua) {
        this.m41Fua = m41Fua;
    }

    public String getM42Fecha() {
        return m42Fecha;
    }

    public void setM42Fecha(String m42Fecha) {
        this.m42Fecha = m42Fecha;
    }

    public String getM42Cie10() {
        return m42Cie10;
    }

    public void setM42Cie10(String m42Cie10) {
        this.m42Cie10 = m42Cie10;
    }

    public String getM42Fua() {
        return m42Fua;
    }

    public void setM42Fua(String m42Fua) {
        this.m42Fua = m42Fua;
    }

    public String getM43Fecha() {
        return m43Fecha;
    }

    public void setM43Fecha(String m43Fecha) {
        this.m43Fecha = m43Fecha;
    }

    public String getM43Cie10() {
        return m43Cie10;
    }

    public void setM43Cie10(String m43Cie10) {
        this.m43Cie10 = m43Cie10;
    }

    public String getM43Fua() {
        return m43Fua;
    }

    public void setM43Fua(String m43Fua) {
        this.m43Fua = m43Fua;
    }

    public String getM44Fecha() {
        return m44Fecha;
    }

    public void setM44Fecha(String m44Fecha) {
        this.m44Fecha = m44Fecha;
    }

    public String getM44Cie10() {
        return m44Cie10;
    }

    public void setM44Cie10(String m44Cie10) {
        this.m44Cie10 = m44Cie10;
    }

    public String getM44Fua() {
        return m44Fua;
    }

    public void setM44Fua(String m44Fua) {
        this.m44Fua = m44Fua;
    }

    public String getM5Fecha1() {
        return m5Fecha1;
    }

    public void setM5Fecha1(String m5Fecha1) {
        this.m5Fecha1 = m5Fecha1;
    }

    public String getM5Cie101() {
        return m5Cie101;
    }

    public void setM5Cie101(String m5Cie101) {
        this.m5Cie101 = m5Cie101;
    }

    public String getM5Fua1() {
        return m5Fua1;
    }

    public void setM5Fua1(String m5Fua1) {
        this.m5Fua1 = m5Fua1;
    }

    public String getM6Fecha1() {
        return m6Fecha1;
    }

    public void setM6Fecha1(String m6Fecha1) {
        this.m6Fecha1 = m6Fecha1;
    }

    public String getM6Cie101() {
        return m6Cie101;
    }

    public void setM6Cie101(String m6Cie101) {
        this.m6Cie101 = m6Cie101;
    }

    public String getM6Fua1() {
        return m6Fua1;
    }

    public void setM6Fua1(String m6Fua1) {
        this.m6Fua1 = m6Fua1;
    }

    public String getM7Fecha1() {
        return m7Fecha1;
    }

    public void setM7Fecha1(String m7Fecha1) {
        this.m7Fecha1 = m7Fecha1;
    }

    public String getM7Cie101() {
        return m7Cie101;
    }

    public void setM7Cie101(String m7Cie101) {
        this.m7Cie101 = m7Cie101;
    }

    public String getM7Fua1() {
        return m7Fua1;
    }

    public void setM7Fua1(String m7Fua1) {
        this.m7Fua1 = m7Fua1;
    }

    public String getM8Fecha1() {
        return m8Fecha1;
    }

    public void setM8Fecha1(String m8Fecha1) {
        this.m8Fecha1 = m8Fecha1;
    }

    public String getM8Cie101() {
        return m8Cie101;
    }

    public void setM8Cie101(String m8Cie101) {
        this.m8Cie101 = m8Cie101;
    }

    public String getM8Fua1() {
        return m8Fua1;
    }

    public void setM8Fua1(String m8Fua1) {
        this.m8Fua1 = m8Fua1;
    }

    public String getM9Fecha1() {
        return m9Fecha1;
    }

    public void setM9Fecha1(String m9Fecha1) {
        this.m9Fecha1 = m9Fecha1;
    }

    public String getM9Cie101() {
        return m9Cie101;
    }

    public void setM9Cie101(String m9Cie101) {
        this.m9Cie101 = m9Cie101;
    }

    public String getM9Fua1() {
        return m9Fua1;
    }

    public void setM9Fua1(String m9Fua1) {
        this.m9Fua1 = m9Fua1;
    }

    public String getM10Fecha1() {
        return m10Fecha1;
    }

    public void setM10Fecha1(String m10Fecha1) {
        this.m10Fecha1 = m10Fecha1;
    }

    public String getM10Cie101() {
        return m10Cie101;
    }

    public void setM10Cie101(String m10Cie101) {
        this.m10Cie101 = m10Cie101;
    }

    public String getM10Fua1() {
        return m10Fua1;
    }

    public void setM10Fua1(String m10Fua1) {
        this.m10Fua1 = m10Fua1;
    }

    public String getM11Fecha2() {
        return m11Fecha2;
    }

    public void setM11Fecha2(String m11Fecha2) {
        this.m11Fecha2 = m11Fecha2;
    }

    public String getM11Cie102() {
        return m11Cie102;
    }

    public void setM11Cie102(String m11Cie102) {
        this.m11Cie102 = m11Cie102;
    }

    public String getM11Fua2() {
        return m11Fua2;
    }

    public void setM11Fua2(String m11Fua2) {
        this.m11Fua2 = m11Fua2;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsCcd[ rsCcd=" + rsCcd + " ]";
    }

    /**
     * @return the cn
     */
    public Connection getCn() {
        return cn;
    }

    /**
     * @param cn the cn to set
     */
    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
}
