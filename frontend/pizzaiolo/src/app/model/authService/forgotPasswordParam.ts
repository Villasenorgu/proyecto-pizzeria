/**
 * Identity authorization service
 * REST API to manage user's registration and authentication, role management and token generation and validation
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

/**
 * Parameters required to request a reset link
 */
export interface ForgotPasswordParam { 
    /**
     * The email address to sent the link to
     */
    email: string;
}