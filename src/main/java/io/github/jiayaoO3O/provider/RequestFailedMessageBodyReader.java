/*
 * Copyright (c) 2021 jiayao hpv-finder is licensed under Mulan PSL v2. You can use this software according to the terms and conditions of the Mulan PSL v2. You may obtain a copy of Mulan PSL v2 at: http://license.coscl.org.cn/MulanPSL2 THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE. See the Mulan PSL v2 for more details.
 */

package io.github.jiayaoO3O.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.jboss.resteasy.reactive.common.util.EmptyInputStream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Created by jiayao on 2021/10/26.
 */
@ApplicationScoped
@Named("RequestFailedMessageBodyReader")
public class RequestFailedMessageBodyReader implements MessageBodyReader<Object> {
    //JacksonBasicMessageBodyReader

    protected final ObjectReader reader;

    @Inject
    public RequestFailedMessageBodyReader(ObjectMapper mapper) {
        this.reader = mapper.reader();
    }

    @Override
    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        try {
            return this.doReadFrom(type, genericType, entityStream);
        } catch(MismatchedInputException var8) {
            throw new WebApplicationException(var8, Response.Status.BAD_REQUEST);
        }
    }

    private Object doReadFrom(Class<Object> type, Type genericType, InputStream entityStream) throws IOException {
        return entityStream instanceof EmptyInputStream ? null : this.reader.forType(this.reader.getTypeFactory()
                        .constructType((Type) (genericType != null ? genericType : type)))
                .readValue(entityStream);
    }

}
