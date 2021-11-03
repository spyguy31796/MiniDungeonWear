package com.spylabs.MiniDungeonWear.ClickHandlers;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.spylabs.MiniDungeonWear.GameActivity;

public class MenuClickHandlers {
    public static View.OnClickListener getStartButtonClickHandler() {
        return v -> {
            Intent intent = new Intent(v.getContext(), GameActivity.class);
            startActivity(v.getContext(), intent, Bundle.EMPTY);
        };
    }
}
