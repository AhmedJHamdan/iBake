package com.example.ahmed.ibake.Widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class WidgetRemoteService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetRemoteViewFactory(getApplicationContext());
    }
}
