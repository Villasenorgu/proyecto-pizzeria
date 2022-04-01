/**
 * Api Documentation
 * Api Documentation
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

/**
 * Version editable de las pizzas.
 */
export interface PizzasEditableReq { 
    /**
     * Texto del comentario.
     */
    comentario?: string;
    /**
     * Fecha de creación del comentario.
     */
    date?: Date;
    /**
     * Identificador del comentario.
     */
    idComentario?: number;
    /**
     * Identificador de la pizza.
     */
    idPizza?: number;
    /**
     * Identificador del usuario que genera el comentario.
     */
    idUsuario?: string;
    /**
     * Puntuación que se añade al comentario.
     */
    rating?: number;
}