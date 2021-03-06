package com.hypnoticocelot.jaxrs.doclet.model;

import com.google.common.base.Objects;

import java.util.List;

public class Method {
    private HttpMethod method;
    private String methodName;
    private List<ApiParameter> apiParameters;
    private List<String> consumes;
    private List<String> produces;
    private List<ApiResponseMessage> responseMessages;
    private String firstSentence;
    private String comment;
    private String returnType;
    private String path;

    @SuppressWarnings("unused")
    private Method() {
    }

    public Method(HttpMethod method, String methodName, String path, List<ApiParameter> apiParameters,
                    List<String> consumes, List<String> produces, List<ApiResponseMessage> responseMessages,
                    String firstSentence, String comment, String returnType) {
        this.method = method;
        this.methodName = methodName;
        this.path = path;
        this.apiParameters = apiParameters;
        this.consumes = consumes;
        this.produces = produces;
        this.responseMessages = responseMessages;
        this.firstSentence = firstSentence;
        this.comment = comment;
        this.returnType = returnType;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getPath() {
        return path;
    }

    public List<ApiParameter> getParameters() {
        return apiParameters;
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

    public String getFirstSentence() {
        return firstSentence;
    }

    public String getComment() {
        return comment;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Method that = (Method) o;
        return Objects.equal(method, that.method)
                && Objects.equal(methodName, that.methodName)
                && Objects.equal(apiParameters, that.apiParameters)
                && Objects.equal(consumes, that.consumes)
                && Objects.equal(produces, that.produces)
                && Objects.equal(responseMessages, that.responseMessages)
                && Objects.equal(firstSentence, that.firstSentence)
                && Objects.equal(comment, that.comment)
                && Objects.equal(returnType, that.returnType)
                && Objects.equal(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(method, methodName, apiParameters, consumes, produces, responseMessages, firstSentence,
                        comment, returnType, path);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("method", method)
                .add("methodName", methodName)
                .add("apiParameters", apiParameters)
                .add("consumes", consumes)
                .add("produces", produces)
                .add("responseMessages", responseMessages)
                .add("firstSentence", firstSentence)
                .add("comment", comment)
                .add("returnType", returnType)
                .add("path", path)
                .toString();
    }
}
