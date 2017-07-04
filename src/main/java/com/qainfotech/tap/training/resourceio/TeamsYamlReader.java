package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

//import org.testng.internal.Yaml;


/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsYamlReader{
    

    List<Individual> listind;
    List<Team> listtem;
    
    
	  public TeamsYamlReader() throws ObjectNotFoundException
	  {
   ClassLoader loader = this.getClass().getClassLoader();
   InputStream file =  loader.getResourceAsStream("db.yaml");
   Yaml yaml = new Yaml();
   Map<String,List<Object>>  map = (Map<String,List<Object>>) yaml.load(file);   
    
    
  
	ArrayList<Individual> individuals = (ArrayList)map.get("individuals");  
	listind = new ArrayList<Individual>();
   for(int i=0;i<individuals.size();i++)
    {   
    	Map<String,Object> map1 = (Map<String,Object>) individuals.get(i);
    	Individual ind = new Individual(map1);
    	listind.add(ind);
    	
    }
    
    
    
    ArrayList<Team> teams = (ArrayList)map.get("teams");
    //  Map<String,Object> map1 = (Map<String,Object>) individuals.get(i);
  	listtem= new ArrayList<Team>();
  	Map<String,Object> map1;
  	Map<String,Object> map11 = new HashMap<String,Object>();
      for(int i=0;i<teams.size();i++)
      {
      map1 = (Map<String,Object>) teams.get(i);
      map11.put("name", map1.get("name"));
      map11.put("id", map1.get("id"));
      List list1 =(List)map1.get("members");
      List<Individual> list = new ArrayList<Individual>();
      for(int j=0;j<list1.size();j++)
      {  int id = (int)list1.get(j);
    	  Individual  ind = getIndividualById(id);
    	  list.add(ind);
      }
      map11.put("members", list);
      
      	Team tem = new Team(map11);
      	System.out.println(tem.getName()+""+tem.getId()+"dhuadhad"+tem.getMembers().size());
      	listtem.add(tem);
      }
    
	  }
	
	/**
     * get a list of individual objects from db yaml file
     * 
     * @return 
     */
	
	
    public List<Individual> getListOfIndividuals(){

      
        return listind;
    }
    
    /**
     * get individual object by id
     * 
     * @param id individual id
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     */
    public Individual getIndividualById(Integer id) throws ObjectNotFoundException{
    //    throw new UnsupportedOperationException("Not implemented.");
    	Individual ind = null;
		int flag = 0;
		Iterator<Individual> itr = listind.iterator();
		while (itr.hasNext()) {
			ind = itr.next();
			int a = id;
			int b = ind.getId();
			if (a == b) {
				flag = 1;
				break;
			}

		}
		if (flag == 1)
			return ind;
		else
			throw new ObjectNotFoundException("Individual", "id", id.toString());

    }
    
    /**
     * get individual object by name
     * 
     * @param name
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     */
    public Individual getIndividualByName(String name) throws ObjectNotFoundException{
   //     throw new UnsupportedOperationException("Not implemented.");
    	Individual ind = null;
		Iterator<Individual> itr = listind.iterator();
		int flag = 0;

		while (itr.hasNext()) {
			ind = itr.next();
			String a = name;
			String b = ind.getName();

			if (a.equals(b)) {
				flag = 1;
				break;
			}

		}
		if (flag == 0)
			throw new ObjectNotFoundException("Individual", "Name", name);
		else
			return ind;

    }
    
    
    /**
     * get a list of individual objects who are not active
     * 
     * @return List of inactive individuals object
     */
    public List<Individual> getListOfInactiveIndividuals(){
     //   throw new UnsupportedOperationException("Not implemented.");
    	List<Individual> list1 = new ArrayList<Individual>();
		Iterator<Individual> itr = listind.iterator();
		while (itr.hasNext()) {
			Individual ind = itr.next();
			Boolean a = false;
			Boolean b = ind.isActive();
			if (a == b) {
				list1.add(ind);
			}

		}
		return list1;
    }
    
    /**
     * get a list of individual objects who are active
     * 
     * @return List of active individuals object
     */
    public List<Individual> getListOfActiveIndividuals(){
   //     throw new UnsupportedOperationException("Not implemented.");
    	List<Individual> list1 = new ArrayList<Individual>();

		Iterator<Individual> itr = listind.iterator();
		while (itr.hasNext()) {
			Individual ind = itr.next();
			Boolean a = true;
			Boolean b = ind.isActive();
			if (a == b) {
				list1.add(ind);
			}

		}
		return list1;
    
    }
    
    /**
     * get a list of team objects from db yaml
     * 
     * @return 
     */
    public List<Team> getListOfTeams(){
    	
          return listtem;
    }
}
