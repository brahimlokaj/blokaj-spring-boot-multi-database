package org.blokaj.multidb.models;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObject<T> {

    private HttpStatus status;

    private T data;
}
