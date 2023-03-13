package com.refah.walletwrapper.model.dto;


public class ItemBalance {
    private ChildItemBalance Source;
    private ChildItemBalance Target;
    private Long Amount;
    private String Description = "transfer data";

    public ItemBalance() {
    }

    public ItemBalance(ChildItemBalance source, ChildItemBalance target, Long amount) {
        Source = source;
        Target = target;
        Amount = amount;
    }

    public ChildItemBalance getSource() {
        return Source;
    }

    public void setSource(ChildItemBalance source) {
        Source = source;
    }

    public ChildItemBalance getTarget() {
        return Target;
    }

    public void setTarget(ChildItemBalance target) {
        Target = target;
    }

    public Long getAmount() {
        return Amount;
    }

    public void setAmount(Long amount) {
        Amount = amount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
