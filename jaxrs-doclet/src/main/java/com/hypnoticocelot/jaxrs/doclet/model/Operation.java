package com.hypnoticocelot.jaxrs.doclet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import com.hypnoticocelot.jaxrs.doclet.parser.AnnotationHelper;

import java.util.List;

import static com.google.common.base.Strings.emptyToNull;

public class Operation {

    private HttpMethod httpMethod;
    private String nickname;
    private String responseClass; // void, primitive, complex or a container
    private List<ApiParameter> parameters;
    private List<String> consumes;
    private List<String> produces;
    private String summary; // cap at 60 characters for readability in the UI
    private String notes;

    @JsonProperty("errorResponses")                    // swagger 1.1 name
    private List<ApiResponseMessage> responseMessages; // swagger 1.2 name

    @SuppressWarnings("unused")
    private Operation() {
    }

    public Operation(Method method) {
        this.httpMethod = method.getMethod();
        this.nickname = emptyToNull(method.getMethodName());
        this.responseClass = emptyToNull(AnnotationHelper.typeOf(method.getReturnType()));
        this.parameters = method.getParameters().isEmpty() ? null : method.getParameters();
        this.consumes = method.getConsumes().isEmpty() ? null : method.getConsumes();
        this.produces = method.getProduces().isEmpty() ? null : method.getProduces();
        this.responseMessages = method.getResponseMessages().isEmpty() ? null : method.getResponseMessages();
        this.summary = emptyToNull(method.getFirstSentence());
        this.notes = emptyToNull(method.getComment());
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getNickname() {
        return nickname;
    }

    public String getResponseClass() {
        return responseClass;
    }

    public List<ApiParameter> getParameters() {
        return parameters;
    }

    public List<String> getConsumes() {
        return consumes;
    }

    public List<String> getProduces() {
        return produces;
    }
    
    public List<ApiResponseMessage> getResponseMessages() {
        return responseMessages;
    }

    public String getSummary() {
        return summary;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation that = (Operation) o;
        return Objects.equal(httpMethod, that.httpMethod)
                && Objects.equal(nickname, that.nickname)
                && Objects.equal(responseClass, that.responseClass)
                && Objects.equal(parameters, that.parameters)
                && Objects.equal(consumes, that.consumes)
                && Objects.equal(produces, that.produces)
                && Objects.equal(responseMessages, that.responseMessages)
                && Objects.equal(summary, that.summary)
                && Objects.equal(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(httpMethod, nickname, responseClass, parameters, consumes, produces, responseMessages,
                        summary, notes);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("httpMethod", httpMethod)
                .add("nickname", nickname)
                .add("responseClass", responseClass)
                .add("parameters", parameters)
                .add("consumes", consumes)
                .add("produces", produces)
                .add("responseMessages", responseMessages)
                .add("summary", summary)
                .add("notes", notes)
                .toString();
    }
}
