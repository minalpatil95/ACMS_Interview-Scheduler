package com.minal.scheduler.model;

public enum EventType {

    HIRING() {
        @Override
        public String getType() {
            return "Hiring";
        }
    },
    ONSITE(){
        @Override
        public String getType() {
            return "Onsite";
        }
    };

    public abstract String getType();
}
