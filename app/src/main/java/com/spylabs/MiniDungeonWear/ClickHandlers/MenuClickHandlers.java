package com.spylabs.MiniDungeonWear.ClickHandlers;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.spylabs.MiniDungeonWear.Activities.GameActivity;
import com.spylabs.MiniDungeonWear.Activities.SettingsActivity;

public class MenuClickHandlers {
    public static View.OnClickListener getStartButtonClickHandler() {
        return v -> {
            Intent intent = new Intent(v.getContext(), GameActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(v.getContext(), intent, Bundle.EMPTY);
        };
    }

    public static View.OnClickListener getSettingsButtonClickHandler() {
        return v -> {
            Intent intent = new Intent(v.getContext(), SettingsActivity.class);
            startActivity(v.getContext(), intent, Bundle.EMPTY);
        };
    }
}
