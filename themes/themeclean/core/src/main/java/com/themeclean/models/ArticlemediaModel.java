package com.themeclean.models;

import com.peregrine.nodetypes.models.AbstractComponent;
import com.peregrine.nodetypes.models.IComponent;
import com.peregrine.nodetypes.models.Container;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/*
    //GEN[:DATA
    {
  "definitions": {
    "Articlemedia": {
      "type": "object",
      "x-type": "component",
      "properties": {
        "text": {
          "type": "string",
          "x-source": "inject",
          "x-form-label": "Text",
          "x-form-type": "texteditor"
        },
        "mediaref": {
          "x-form-type": "reference",
          "type": "object",
          "x-type": "component",
          "properties": {
            "mediatype": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Media type",
              "x-form-type": "materialradio",
              "properties": {
                "image": {
                  "x-form-name": "Image",
                  "x-form-value": "image"
                },
                "color": {
                  "x-form-name": "Video",
                  "x-form-value": "video"
                }
              }
            },
            "imagesrc": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Image Source",
              "x-form-visible": "model.mediatype == 'image'",
              "x-form-type": "pathbrowser",
              "x-form-browserRoot": "/content/assets"
            },
            "videosrc": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Video Source",
              "x-form-visible": "model.mediatype == 'video'",
              "x-form-type": "pathbrowser",
              "x-form-browserRoot": "/content/assets"
            },
            "mediawidth": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Width",
              "x-form-type": "range",
              "x-form-min": 10,
              "x-form-max": 100
            },
            "mediacaption": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Caption",
              "x-form-type": "text"
            }
          }
        },
        "bgref": {
          "x-form-type": "reference",
          "type": "object",
          "x-type": "component",
          "properties": {
            "backgroundtype": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Background Type",
              "x-form-type": "materialradio",
              "x-default": "color",
              "properties": {
                "image": {
                  "x-form-name": "Image",
                  "x-form-value": "image"
                },
                "color": {
                  "x-form-name": "Color",
                  "x-form-value": "color"
                },
                "gradient": {
                  "x-form-name": "Gradient",
                  "x-form-value": "gradient"
                },
                "video": {
                  "x-form-name": "Video",
                  "x-form-value": "video"
                }
              }
            },
            "bgvideo": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Background Video",
              "x-form-type": "pathbrowser",
              "x-form-visible": "model.backgroundtype == 'video'",
              "x-defualt": "https://www.youtube.com/embed/Ju86mknumYM"
            },
            "bgimage": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Background Image",
              "x-form-type": "pathbrowser",
              "x-form-visible": "model.backgroundtype == 'image'"
            },
            "overlay": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Overlay",
              "x-form-type": "materialswitch",
              "x-form-visible": "model.backgroundtype == 'image'"
            },
            "overlaycolor": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Overlay Color",
              "x-form-type": "color",
              "x-form-visible": "model.overlay == 'true' and model.backgroundtype == 'image'",
              "x-default": "#ffffff"
            },
            "overlayopacity": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Overlay opacity",
              "x-form-type": "range",
              "x-form-min": 0,
              "x-form-max": 100,
              "x-form-visible": "model.overlay == 'true' and model.backgroundtype == 'image'",
              "x-default": "50"
            },
            "bgcolor": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Background Color",
              "x-form-type": "color",
              "x-form-visible": "model.backgroundtype == 'color' or model.backgroundtype == 'gradient'",
              "x-default": "#ffffff"
            },
            "color2": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Color 2",
              "x-form-type": "color",
              "x-form-visible": "model.backgroundtype == 'gradient'",
              "x-default": "#c0c0c0"
            },
            "toppadding": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Top Padding",
              "x-form-type": "range",
              "x-form-min": 0,
              "x-form-max": 120,
              "x-form-visible": "model.fullheight != 'true'"
            },
            "bottompadding": {
              "type": "string",
              "x-source": "inject",
              "x-form-label": "Bottom Padding",
              "x-form-type": "range",
              "x-form-min": 0,
              "x-form-max": 120,
              "x-form-visible": "model.fullheight != 'true'"
            }
          }
        }
      }
    }
  },
  "name": "Articlemedia",
  "componentPath": "themeclean/components/articlemedia",
  "package": "com.themeclean.models",
  "modelName": "Articlemedia",
  "classNameParent": "AbstractComponent"
}
//GEN]
*/

//GEN[:DEF
@Model(
        adaptables = Resource.class,
        resourceType = "themeclean/components/articlemedia",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = IComponent.class
)
@Exporter(
        name = "jackson",
        extensions = "json"
)

//GEN]
public class ArticlemediaModel extends AbstractComponent {

    public ArticlemediaModel(Resource r) { super(r); }

    //GEN[:INJECT
    	/* {"type":"string","x-source":"inject","x-form-label":"Text","x-form-type":"texteditor"} */
	@Inject
	private String text;

	/* {"type":"string","x-source":"inject","x-form-label":"Media type","x-form-type":"materialradio","properties":{"image":{"x-form-name":"Image","x-form-value":"image"},"color":{"x-form-name":"Video","x-form-value":"video"}}} */
	@Inject
	private String mediatype;

	/* {"type":"string","x-source":"inject","x-form-label":"Image Source","x-form-visible":"model.mediatype == 'image'","x-form-type":"pathbrowser","x-form-browserRoot":"/content/assets"} */
	@Inject
	private String imagesrc;

	/* {"type":"string","x-source":"inject","x-form-label":"Video Source","x-form-visible":"model.mediatype == 'video'","x-form-type":"pathbrowser","x-form-browserRoot":"/content/assets"} */
	@Inject
	private String videosrc;

	/* {"type":"string","x-source":"inject","x-form-label":"Width","x-form-type":"range","x-form-min":10,"x-form-max":100} */
	@Inject
	private String mediawidth;

	/* {"type":"string","x-source":"inject","x-form-label":"Caption","x-form-type":"text"} */
	@Inject
	private String mediacaption;

	/* {"type":"string","x-source":"inject","x-form-label":"Background Type","x-form-type":"materialradio","x-default":"color","properties":{"image":{"x-form-name":"Image","x-form-value":"image"},"color":{"x-form-name":"Color","x-form-value":"color"},"gradient":{"x-form-name":"Gradient","x-form-value":"gradient"},"video":{"x-form-name":"Video","x-form-value":"video"}}} */
	@Inject
	@Default(values ="color")
	private String backgroundtype;

	/* {"type":"string","x-source":"inject","x-form-label":"Background Video","x-form-type":"pathbrowser","x-form-visible":"model.backgroundtype == 'video'","x-defualt":"https://www.youtube.com/embed/Ju86mknumYM"} */
	@Inject
	@Default(values ="https://www.youtube.com/embed/Ju86mknumYM")
	private String bgvideo;

	/* {"type":"string","x-source":"inject","x-form-label":"Background Image","x-form-type":"pathbrowser","x-form-visible":"model.backgroundtype == 'image'"} */
	@Inject
	private String bgimage;

	/* {"type":"string","x-source":"inject","x-form-label":"Overlay","x-form-type":"materialswitch","x-form-visible":"model.backgroundtype == 'image'"} */
	@Inject
	private String overlay;

	/* {"type":"string","x-source":"inject","x-form-label":"Overlay Color","x-form-type":"color","x-form-visible":"model.overlay == 'true' and model.backgroundtype == 'image'","x-default":"#ffffff"} */
	@Inject
	@Default(values ="#ffffff")
	private String overlaycolor;

	/* {"type":"string","x-source":"inject","x-form-label":"Overlay opacity","x-form-type":"range","x-form-min":0,"x-form-max":100,"x-form-visible":"model.overlay == 'true' and model.backgroundtype == 'image'","x-default":"50"} */
	@Inject
	@Default(values ="50")
	private String overlayopacity;

	/* {"type":"string","x-source":"inject","x-form-label":"Background Color","x-form-type":"color","x-form-visible":"model.backgroundtype == 'color' or model.backgroundtype == 'gradient'","x-default":"#ffffff"} */
	@Inject
	@Default(values ="#ffffff")
	private String bgcolor;

	/* {"type":"string","x-source":"inject","x-form-label":"Color 2","x-form-type":"color","x-form-visible":"model.backgroundtype == 'gradient'","x-default":"#c0c0c0"} */
	@Inject
	@Default(values ="#c0c0c0")
	private String color2;

	/* {"type":"string","x-source":"inject","x-form-label":"Top Padding","x-form-type":"range","x-form-min":0,"x-form-max":120,"x-form-visible":"model.fullheight != 'true'"} */
	@Inject
	private String toppadding;

	/* {"type":"string","x-source":"inject","x-form-label":"Bottom Padding","x-form-type":"range","x-form-min":0,"x-form-max":120,"x-form-visible":"model.fullheight != 'true'"} */
	@Inject
	private String bottompadding;


//GEN]

    //GEN[:GETTERS
    	/* {"type":"string","x-source":"inject","x-form-label":"Text","x-form-type":"texteditor"} */
	public String getText() {
		return text;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Media type","x-form-type":"materialradio","properties":{"image":{"x-form-name":"Image","x-form-value":"image"},"color":{"x-form-name":"Video","x-form-value":"video"}}} */
	public String getMediatype() {
		return mediatype;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Image Source","x-form-visible":"model.mediatype == 'image'","x-form-type":"pathbrowser","x-form-browserRoot":"/content/assets"} */
	public String getImagesrc() {
		return imagesrc;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Video Source","x-form-visible":"model.mediatype == 'video'","x-form-type":"pathbrowser","x-form-browserRoot":"/content/assets"} */
	public String getVideosrc() {
		return videosrc;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Width","x-form-type":"range","x-form-min":10,"x-form-max":100} */
	public String getMediawidth() {
		return mediawidth;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Caption","x-form-type":"text"} */
	public String getMediacaption() {
		return mediacaption;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Background Type","x-form-type":"materialradio","x-default":"color","properties":{"image":{"x-form-name":"Image","x-form-value":"image"},"color":{"x-form-name":"Color","x-form-value":"color"},"gradient":{"x-form-name":"Gradient","x-form-value":"gradient"},"video":{"x-form-name":"Video","x-form-value":"video"}}} */
	public String getBackgroundtype() {
		return backgroundtype;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Background Video","x-form-type":"pathbrowser","x-form-visible":"model.backgroundtype == 'video'","x-defualt":"https://www.youtube.com/embed/Ju86mknumYM"} */
	public String getBgvideo() {
		return bgvideo;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Background Image","x-form-type":"pathbrowser","x-form-visible":"model.backgroundtype == 'image'"} */
	public String getBgimage() {
		return bgimage;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Overlay","x-form-type":"materialswitch","x-form-visible":"model.backgroundtype == 'image'"} */
	public String getOverlay() {
		return overlay;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Overlay Color","x-form-type":"color","x-form-visible":"model.overlay == 'true' and model.backgroundtype == 'image'","x-default":"#ffffff"} */
	public String getOverlaycolor() {
		return overlaycolor;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Overlay opacity","x-form-type":"range","x-form-min":0,"x-form-max":100,"x-form-visible":"model.overlay == 'true' and model.backgroundtype == 'image'","x-default":"50"} */
	public String getOverlayopacity() {
		return overlayopacity;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Background Color","x-form-type":"color","x-form-visible":"model.backgroundtype == 'color' or model.backgroundtype == 'gradient'","x-default":"#ffffff"} */
	public String getBgcolor() {
		return bgcolor;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Color 2","x-form-type":"color","x-form-visible":"model.backgroundtype == 'gradient'","x-default":"#c0c0c0"} */
	public String getColor2() {
		return color2;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Top Padding","x-form-type":"range","x-form-min":0,"x-form-max":120,"x-form-visible":"model.fullheight != 'true'"} */
	public String getToppadding() {
		return toppadding;
	}

	/* {"type":"string","x-source":"inject","x-form-label":"Bottom Padding","x-form-type":"range","x-form-min":0,"x-form-max":120,"x-form-visible":"model.fullheight != 'true'"} */
	public String getBottompadding() {
		return bottompadding;
	}


//GEN]

    //GEN[:CUSTOMGETTERS
    //GEN]

}