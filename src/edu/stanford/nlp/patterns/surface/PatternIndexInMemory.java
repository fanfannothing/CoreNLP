package edu.stanford.nlp.patterns.surface;

import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.util.concurrent.ConcurrentHashIndex;

import java.io.IOException;

/**
 * Created by sonalg on 10/24/14.
 */
public class PatternIndexInMemory extends PatternIndex{

  ConcurrentHashIndex<SurfacePattern> patternIndex;

  public PatternIndexInMemory(){
    patternIndex = new ConcurrentHashIndex<SurfacePattern>();
  }

  public PatternIndexInMemory(ConcurrentHashIndex<SurfacePattern> patternIndex ){
     this.patternIndex = patternIndex;
    System.out.println("read " + patternIndex.size()  + " patterns ");

  }

  @Override
  public int addToIndex(SurfacePattern p) {
      return patternIndex.addToIndex(p);
  }

  @Override
  public SurfacePattern get(int i) {
    SurfacePattern p = patternIndex.get(i);
    assert p!=null;
    return p;
  }

  @Override
  public void close() {
  //nothing to do
  }

  @Override
  public Integer indexOf(SurfacePattern pat) {
    return patternIndex.indexOf(pat);
  }

  @Override
  public void save(String dir) throws IOException {
    IOUtils.writeObjectToFile(patternIndex, dir +"/patternsindex.ser");
  }

  @Override
  public void finishCommit() {
    //nothing to do
  }

  public static PatternIndexInMemory load(String dir){
    try {
      return new PatternIndexInMemory(IOUtils.readObjectFromFile(dir + "/patternsindex.ser"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }

  }

}
