#include "pebble.h"

#include "Adventure.h"
#include "Battle.h"
#include "Character.h"
#include "Items.h"
#include "Logging.h"
#include "Menu.h"
#include "Persistence.h"
#include "Shop.h"
#include "UILayers.h"
#include "Utils.h"

	 
// Called once per minute
void handle_minute_tick(struct tm* tick_time, TimeUnits units_changed) 
{
	UpdateClock();
	UpdateAdventure();
}

void InitializeGameData(void)
{
	if(!LoadPersistedData())
		ResetGame();
}

void ResetGame(void)
{
#if ALLOW_STAT_SHOP
	ResetStatPointsPurchased();
#endif
	InitializeCharacter();
	ResetFloor();
	ClearInventory();
	
	SavePersistedData();
}

void handle_init() {
	
	DEBUG_LOG("Start MiniDungeon");
	time_t now = time(NULL);
	
	srand(now);
	DEBUG_LOG("Srand");
//	InitializeExitConfirmationWindow();
	
	DEBUG_LOG("exit window initialized");
	
	handle_minute_tick(NULL, MINUTE_UNIT);
	DEBUG_LOG("First handle second");
	
	InitializeGameData();
	DEBUG_LOG("InitializeGameData");
	ShowAdventureWindow();
	DEBUG_LOG("Show adventure window");
	tick_timer_service_subscribe(MINUTE_UNIT, &handle_minute_tick);
}

void handle_deinit() 
{
	SavePersistedData();
	UnloadBackgroundImage();
	UnloadMainBmpImage();
	UnloadTextLayers();
}

// The main event/run loop for our app
int main(void) {
  handle_init();
  app_event_loop();
  handle_deinit();
}
