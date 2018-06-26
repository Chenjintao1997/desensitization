package cjt.components;

import cjt.annotations.InfoMask;
import cjt.annotations.InfoMasks;
import cjt.beans.InfoOperator;
import cjt.utils.OperationObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
public class InfoMasksAdvice {
    private static final Logger LOG = Logger.getLogger(InfoMasksAdvice.class);
    Map<String,List<InfoOperator>> operatorCache = new HashMap<>();

    @Around("execution(public * *(..)) && @annotation(cjt.annotations.InfoMasks)")
    public Object doAround(ProceedingJoinPoint joint){
        Object result = null;
        try {
            MethodSignature signature = (MethodSignature) joint.getSignature();
            String methodName = signature.getMethod().getName();
            InfoMasks infoMasks = signature.getMethod().getAnnotation(InfoMasks.class);
            List<InfoOperator> operators = new ArrayList<>();
            if (CollectionUtils.isEmpty(operatorCache.get(methodName))){
                for (InfoMask infoMask:infoMasks.value()){
                    InfoOperator infoOperator = infoMask.value().newInstance();
                    infoOperator.setWhiteListKey(infoMask.whiteListKey());
                    infoOperator.setBlackListKey(infoMask.blackListKey());
                    operators.add(infoOperator);
                }
                operatorCache.put(methodName,operators);
            }else operators = operatorCache.get(methodName);
            result = joint.proceed();
            final List<InfoOperator> finalOperators = operators;
            OperationObject.doOperation(result, new OperationObject.Visitor() {
                @Override
                public void visit(Object object, Object indexOrKey, Object value) {
                    for (InfoOperator operator : finalOperators){
                        if(!operator.shouldOperator(value.toString())) continue;
                        if(!CollectionUtils.isEmpty(operator.getBlackListKey()) && !operator.getBlackListKey().contains(indexOrKey.toString())) continue;
                        if(!CollectionUtils.isEmpty(operator.getWhiteListKey()) && operator.getWhiteListKey().contains(indexOrKey.toString())) continue;
                        String operatedValue = operator.operator(value.toString());
                        OperationObject.setValue(object,indexOrKey,operatedValue);
                    }
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}
