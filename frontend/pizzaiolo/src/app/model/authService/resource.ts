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
import { InputStream } from './inputStream';

export interface Resource { 
    description?: string;
    file?: Blob;
    filename?: string;
    inputStream?: InputStream;
    open?: boolean;
    readable?: boolean;
    uri?: string;
    url?: string;
}