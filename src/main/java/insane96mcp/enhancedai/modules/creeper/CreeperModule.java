package insane96mcp.enhancedai.modules.creeper;

import insane96mcp.enhancedai.modules.creeper.feature.CreeperAIFeature;
import insane96mcp.enhancedai.setup.Config;
import insane96mcp.insanelib.base.Label;
import insane96mcp.insanelib.base.Module;

@Label(name = "Creeper")
public class CreeperModule extends Module {

	CreeperAIFeature creeperAIFeature;

	public CreeperModule() {
		super(Config.builder);
		this.pushConfig(Config.builder);
		creeperAIFeature = new CreeperAIFeature(this);
		Config.builder.pop();
	}

	@Override
	public void loadConfig() {
		super.loadConfig();
		creeperAIFeature.loadConfig();
	}
}
