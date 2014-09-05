package daniele;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import converter.ValueConverter;
import model.ObjectFactory;
import model.Instance.Network;
import model.Instance.Network.Nodes;
import model.Instance.Network.Nodes.Node;

public class DanieleNodeCoordConverter implements ValueConverter<Network> {

	@Override
	public Network getOutput(String input, HashMap<String, Object> options) {
		ObjectFactory objectFactory = new ObjectFactory();
		Network network = objectFactory.createInstanceNetwork();
		Nodes nodes = objectFactory.createInstanceNetworkNodes();
		
		@SuppressWarnings("unchecked")
		ArrayList<Integer> depots = (ArrayList<Integer>) options.get("DEPOT_SECTION");
		String edgeWeightType = (String) options.get("EDGE_WEIGHT_TYPE");
		
		if(edgeWeightType.equals("EUC_2D")){
			String regex = "^(?<id>[0-9]*)\\s+(?<x>[0-9.-]*)\\s+(?<y>[0-9.-]*)$";
			Pattern pattern = Pattern.compile(regex);
			for(String line : input.split("\n")){
				Matcher matcher = pattern.matcher(line);
				if (matcher.find()) {
					Node node = objectFactory.createInstanceNetworkNodesNode();
					int id = Integer.valueOf(matcher.group("id"));
					int isClient = depots.contains(id) ? 0 : 1;
					node.setId(BigInteger.valueOf(id));
					node.setType(BigInteger.valueOf(isClient));
					node.setCx(Double.valueOf(matcher.group("x")));
					node.setCy(Double.valueOf(matcher.group("y")));
					nodes.getNode().add(node);
				}
			}
			//FIXME EUCLIDEAN
			network.setEuclidean("");
			network.setDecimals(2);
		}
		
		return network;
	}

}