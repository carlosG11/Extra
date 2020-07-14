/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aux2.vo;

/**
 *
 * @author QTK
 */
public class VOUsuario {

    private int id;
    private String usuario;
    private String perfil;
    private String nombre;
    private String paterno;
    private String materno;
    private String password;
    private String calle;
    private String exterior;
    private String interior;
    private String colonia;
    private String municipio;
    private String telefono;
    private String correo;
    private String sucursal;
    private String carrito;

    public VOUsuario() {
        this.id = 0;
        this.usuario = "";
        this.perfil = "";
        this.nombre = "";
        this.paterno = "";
        this.materno = "";
        this.password = "";
        this.calle = "";
        this.exterior = "";
        this.interior = "";
        this.colonia = "";
        this.municipio = "";
        this.telefono = "";
        this.correo = "";
        this.sucursal = "";
        this.carrito = "";
    }

    public String getCarrito() {
        return carrito;
    }

    public void setCarrito(String carrito) {
        this.carrito = carrito;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getExterior() {
        return exterior;
    }

    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

}
