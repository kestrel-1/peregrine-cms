module.exports = {
    convert: function($, f) {
        f.bindPath($)
        f.bindAttribute($, "id", "name")
        f.bindAttribute($, "data-interval", "model.interval")
        f.bindAttribute($, "data-pause", "model.pause")
        f.bindAttribute($, "data-ride", "model.ride")
        f.bindAttribute($, "data-indicators", "model.indicators")
        f.bindAttribute($, "data-controls", "model.controls")
        f.bindAttribute($, "data-wrap", "model.wrap")
        f.bindAttribute($, "data-keyboard", "model.keyboard")
        f.bindAttribute($, "data-per-path", "model.path")
        
        let firstLi = $.find('li').first()
        f.addIf($.find('ol').first(), "model.indicators")
        f.addFor(firstLi, 'model.slides')
        f.bindAttribute(firstLi, "data-target", "`#${name}`")
        f.bindAttribute(firstLi, "data-slide-to", "i")
        
        let firstSlide = $.find('.carousel-item').first()
        let image = $.find('img').first()
        let slideCaption = $.find('.carousel-caption').first()
        let h3 = $.find('h3').first()
        let p = $.find('p').first()
        f.addFor(firstSlide, 'model.slides')
        f.bindAttribute(firstSlide, "class", "`carousel-item ${i === 0 ? 'active' : ''}`")
        f.addIf(image, "item.imagepath")
        f.bindAttribute(image, "src", "item.imagepath")
        f.bindAttribute(image, "alt", "item.alt")
        f.addIf(slideCaption, "item.heading || item.text")
        f.addIf(h3, "item.heading")
        f.mapRichField(h3, "item.heading")
        f.addIf(p, "item.text")
        f.mapRichField(p, "item.text")
        
        let link1 = $.find('a').eq(0)
        let link2 = $.find('a').eq(1)
        f.addIf(link1, "model.controls")
        f.bindAttribute(link1, "href", "`#${name}`")
        f.addIf(link2, "model.controls")
        f.bindAttribute(link2, "href", "`#${name}`")
    }
}