package services;

import java.util.concurrent.atomic.AtomicLong;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private static Map map = new Map("Intersections.csv");
    
    @RequestMapping("/greeting")
    public Apple greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Apple(counter.incrementAndGet(),String.format(template, name));
    }
    @RequestMapping("/path")
    public Path path(@RequestParam(value="p1a", defaultValue="")String p1a,@RequestParam(value="p1b",defaultValue ="") String p1b, @RequestParam(value="p2a", defaultValue="")String p2a,@RequestParam(value="p2b",defaultValue ="") String p2b) {
    	Intersection p1 = new Intersection(34.0639359, -118.41406628, "SANTA MONICA BLVD", "MORENO DR");
    	Intersection p2 = p1;
    	HashMap<String,ArrayList<Intersection>> intersections = map.getConnections();
    	if (!p1a.equals("") && !p2a.equals("")) {
    		
    		for (Intersection i : intersections.get(p1a)) {
    			if (intersections.get(p1b).contains(i)) {
    				System.out.println(i);
    				p1 = i;
    				break;
    			}
    		}
    		for (Intersection i : intersections.get(p2a)) {
    			if (intersections.get(p2b).contains(i)) {
    				System.out.println(i);
    				p2 = i;
    				break;
    			}
    		}
    		
    	}
		//Intersection p2 = new Intersection(34.21834015,-118.58087895,"SUNNY BRAE AVE","LANARK ST");
    	return new Path(map.iterativeDFS(p1, p2),map.getPathDist());
    }
    
    
   
    
    
}
