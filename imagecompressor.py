#!/usr/bin/python 
import Image

foo = Image.open("./pic/Bacon.jpg")

foo = foo.resize((160,300),Image.ANTIALIAS)
foo.save("./pic_cp/image_scaled.jpg",quality=50)
foo.save("./pic_cp/image_scaled_opt.jpg",optimize=True,quality=95)