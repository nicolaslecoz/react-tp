
export interface BaseEntity {
    id: string;
    version: number;
}

export interface BaseEntityWithAudit extends BaseEntity {
    createdAt: Date;
    updatedAt: Date;
}

export interface CustomUserDetails extends UserDetails {
    userId: string;
}

export interface CustomUserDetailsBuilder {
}

export interface StatusObject<T> {
    message: string;
    status: HttpStatus;
    resource: T;
}

export interface StatusObjectBuilder<T> {
}

export interface User {
    id: string;
    name: string;
}

export interface UserBuilder {
}

export interface Board extends BaseEntityWithAudit {
    name: string;
    userId: string;
}

export interface BoardDto {
    columns: ColumnDto[];
    board: Board;
}

export interface BoardDtoBuilder {
}

export interface Card extends BaseEntityWithAudit {
    title: string;
    body: string;
    columnId: string;
    rank: number;
}

export interface CardCreationForm {
    title: string;
    body: string;
    columnId: string;
    boardId: string;
    rank: number;
}

export interface CardCreationFormBuilder {
}

export interface CardDto {
    card: Card;
    boardId: string;
}

export interface CardDtoBuilder {
}

export interface Column extends BaseEntityWithAudit {
    name: string;
    boardId: string;
    rank: number;
}

export interface ColumnDto {
    column: Column;
    cards: CardDto[];
}

export interface ColumnDtoBuilder {
}

export interface JwtResponse {
    jwt: string;
    user: User;
}

export interface JwtResponseBuilder {
}

export interface LoginRequest {
    username: string;
    password: string;
}

export interface GrantedAuthority extends Serializable {
    authority: string;
}

export interface UserDetails extends Serializable {
    enabled: boolean;
    username: string;
    password: string;
    authorities: GrantedAuthority[];
    accountNonExpired: boolean;
    accountNonLocked: boolean;
    credentialsNonExpired: boolean;
}

export interface Serializable {
}

export type HttpStatus = "CONTINUE" | "SWITCHING_PROTOCOLS" | "PROCESSING" | "EARLY_HINTS" | "CHECKPOINT" | "OK" | "CREATED" | "ACCEPTED" | "NON_AUTHORITATIVE_INFORMATION" | "NO_CONTENT" | "RESET_CONTENT" | "PARTIAL_CONTENT" | "MULTI_STATUS" | "ALREADY_REPORTED" | "IM_USED" | "MULTIPLE_CHOICES" | "MOVED_PERMANENTLY" | "FOUND" | "MOVED_TEMPORARILY" | "SEE_OTHER" | "NOT_MODIFIED" | "USE_PROXY" | "TEMPORARY_REDIRECT" | "PERMANENT_REDIRECT" | "BAD_REQUEST" | "UNAUTHORIZED" | "PAYMENT_REQUIRED" | "FORBIDDEN" | "NOT_FOUND" | "METHOD_NOT_ALLOWED" | "NOT_ACCEPTABLE" | "PROXY_AUTHENTICATION_REQUIRED" | "REQUEST_TIMEOUT" | "CONFLICT" | "GONE" | "LENGTH_REQUIRED" | "PRECONDITION_FAILED" | "PAYLOAD_TOO_LARGE" | "REQUEST_ENTITY_TOO_LARGE" | "URI_TOO_LONG" | "REQUEST_URI_TOO_LONG" | "UNSUPPORTED_MEDIA_TYPE" | "REQUESTED_RANGE_NOT_SATISFIABLE" | "EXPECTATION_FAILED" | "I_AM_A_TEAPOT" | "INSUFFICIENT_SPACE_ON_RESOURCE" | "METHOD_FAILURE" | "DESTINATION_LOCKED" | "UNPROCESSABLE_ENTITY" | "LOCKED" | "FAILED_DEPENDENCY" | "TOO_EARLY" | "UPGRADE_REQUIRED" | "PRECONDITION_REQUIRED" | "TOO_MANY_REQUESTS" | "REQUEST_HEADER_FIELDS_TOO_LARGE" | "UNAVAILABLE_FOR_LEGAL_REASONS" | "INTERNAL_SERVER_ERROR" | "NOT_IMPLEMENTED" | "BAD_GATEWAY" | "SERVICE_UNAVAILABLE" | "GATEWAY_TIMEOUT" | "HTTP_VERSION_NOT_SUPPORTED" | "VARIANT_ALSO_NEGOTIATES" | "INSUFFICIENT_STORAGE" | "LOOP_DETECTED" | "BANDWIDTH_LIMIT_EXCEEDED" | "NOT_EXTENDED" | "NETWORK_AUTHENTICATION_REQUIRED";
