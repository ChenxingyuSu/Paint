Chenxingyu Su
20755516 c35su
openjdk version "11.0.8" 2020-07-14
Windows 10, intellij gradle Lenovo YOGA 730 13"

Source of icons:
rubber:https://www.flaticon.com/free-icons/rubber
pen:https://www.flaticon.com/search?word=pen
line: https://www.flaticon.com/search?word=line
barrel: https://www.flaticon.com/search?word=barrel
rectangle: https://www.flaticon.com/search?search-type=icons&word=rectangle&license=&color=&stroke=&current_section=&author_id=&pack_id=&family_id=&style_id=&category_id=
circle: https://www.flaticon.com/search?word=circle
select: https://www.flaticon.com/search?search-type=icons&word=selection+tool&license=&color=&stroke=&current_section=&author_id=&pack_id=&family_id=&style_id=&category_id=
logo: https://www.flaticon.com/search?word=paint

In the toolbar, the upper colorPicker is used to pick drawing color for lines and shapes. 
The lower colorPicker is used to choose color to fill.

At the very bottom, two options to choose. The upper one define the line thickness of line/shape. Lower one defines line style, including solid, dashed, dotted.
Note that the line style can only be chosen and changed when "Line" tool is chosed.
For shapes(rectangle and circle tool), the thickness options include a field called "Fill". If "Fill" is chosed, it will draw a filled shape with the filling color defined in the second colorPicker.
"Fill" option is not available for "Line" tool.

Note that in "DOTTED" mode, when the line is too thick, it will seem like a solid line.

Implemented own feature:
Undo-Redo: allow users to undo-redo multiple steps. Add functionality under Edit-Undo and Edit-Redo menu items.

Features incompleted:
Selection Tool
Preview
Fill: fill is incorrectly implemented. Fill is set to fill the whole canvas.
