package cjt.beans;

import org.apache.commons.collections.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

public abstract class InfoOperator {
    private Set<String> whiteListKey = new HashSet<>();

    private Set<String> blackListKey = new HashSet<>();

    public Set<String> getWhiteListKey() {
        return whiteListKey;
    }

    public void setWhiteListKey(Set<String> whiteListKey) {
        this.whiteListKey = whiteListKey;
    }

    public void setWhiteListKey(String[] whiteListKey) {
        CollectionUtils.addAll(this.whiteListKey,whiteListKey);
    }

    public Set<String> getBlackListKey() {
        return blackListKey;
    }

    public void setBlackListKey(Set<String> blackListKey) {
        this.blackListKey = blackListKey;
    }

    public void setBlackListKey(String[] blackListKey){
        CollectionUtils.addAll(this.blackListKey,blackListKey);
    }

    public abstract boolean shouldOperator(String input);

    public abstract String operator(String input);
}
