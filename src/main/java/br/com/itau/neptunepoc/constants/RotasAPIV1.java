package br.com.itau.neptunepoc.constants;

public class RotasAPIV1 {
    public final static String PATH = "pessoas/v1/inquilinos";
    //SOCIOS
    public final static String CREATE_RELATIONSHIP_PARTNER = "{id_inquilino}/clientes/{id_cliente_pai}/relacionamento_socios";
    public final static String GET_RELATIONSHIP_PARTNER = "{id_inquilino}/clientes/{id_cliente_pai}/relacionamento_socios";
    public final static String PATCH_RELATIONSHIP_PARTNER = "{id_inquilino}/clientes/{id_cliente_pai}/relacionamento_socios/{id_relacionamento}";
    public final static String DELETE_RELATIONSHIP_PARTNER = "{id_inquilino}/clientes/{id_cliente_pai}/relacionamento_socios/{id_relacionamento}";
    //REPRESENTANTES
    public final static String CREATE_RELATIONSHIP_REPRESENTATIVE = "{id_inquilino}/clientes/{id_cliente_pai}/relacionamento_representante";

}
