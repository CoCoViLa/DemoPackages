class AlgorithmVisualizer {
    /*@ specification AlgorithmVisualizer {
          ee.ioc.cs.vsle.synthesize.EvaluationAlgorithm in;
          void visualizerReady;
          in -> visualizerReady {visualize};
    }@*/
    
		public void visualize(ee.ioc.cs.vsle.synthesize.EvaluationAlgorithm algorithm){
			ee.ioc.cs.vsle.synthesize.AlgorithmVisualizer.getInstance().addNewTab( "Generated Program", algorithm );
    }
}
