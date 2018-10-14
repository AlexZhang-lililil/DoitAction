package com.example.orquoll.swen90014_2018_or_quoll.NotifyAction;

import com.example.orquoll.swen90014_2018_or_quoll.entity.Action;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Notification;

public interface TriggerStrategy {
    public Action[] getNotification();
}
