Engine_Greyhole : CroneEngine {

  var <synth;
  
  *new { arg context, doneCallback;
    ^super.new(context, doneCallback);
  }

  alloc {
    SynthDef(\Greyhole, {
      arg out, delayTime=2.0, damp=0.1, size=3.0, diff=0.7, feedback=0.2, modDepth=0.0, modFreq=0.1;
      var sig = {
        Greyhole.ar(SoundIn.ar([0,1]),
          delayTime,
          damp,
          size,
          diff,
          feedback,
          modDepth,
          modFreq
        ); 
      };
      
      Out.ar(out, sig);
    }).add;

    context.server.sync;

    synth = Synth.new(\Greyhole, [
      \out, context.out_b.index,
      \delayTime, 2.0,
      \damp, 0.1,
      \size, 3.0,
      \diff, 0.7,
      \feedback, 0.2,
      \modDepth, 0.0,
      \modFreq, 1.0],
    context.xg);

    this.addCommand("delay_time", "f", {|msg|
      synth.set(\delayTime, msg[1]);
    });
    
    this.addCommand("delay_damp", "f", {|msg|
      synth.set(\damp, msg[1]);
    }); 

    this.addCommand("delay_size", "f", {|msg|
      synth.set(\size, msg[1]);
    });

    this.addCommand("delay_diff", "f", {|msg|
      synth.set(\diff, msg[1]);
    });

    this.addCommand("delay_fdbk", "f", {|msg|
      synth.set(\feedback, msg[1]);
    });

    this.addCommand("delay_mod_depth", "f", {|msg|
      synth.set(\modDepth, msg[1]);
    });

    this.addCommand("delay_mod_freq", "f", {|msg|
      synth.set(\modFreq, msg[1]);
    });
  }

  free {
    synth.free;
  }
}
