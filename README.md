# FEA_Solids


required libraries can be found here:
 * [MatrixLibrary](https://github.com/Luecx/MatrixLibrary)
 * [CellLibrary](https://github.com/Luecx/CellLibrary)



A library to calculate stresses in structures.
Implemented are 2d triangles but this could easily be extended.




------

## MeshEditor

The main-gui class is [MeshEditor](https://github.com/Luecx/FEA_Solids/blob/master/src/solids_2d/visual/application/MeshEditor.java)

### important notes
 * a cartesian coordinate system is used. `x` goes to the 'right' and `y` goes to the 'top'
 * the display has always the same `x` and `y`-range which can be adjusted under .
 

### creating meshes
Mesh > new Mesh (`ctrl + N`) offers 2 templates to choose from including a sketch:
 1. rectangle form defined by `width` and `height`.\
 `subdivisions x` defines the amount of subdivisons in `x`-direction.\
 `subdivisions y` defines the amount of subdivisons in `y`-direction.
   
 2. rectangle form defined by `R1`,`R2`,`L`,`phi`. \
    `R1` is the inner radius.\
    `R2` is the outer radius.\
    `L` is the length of the non curved part at both ends of the arc.\
    `phi` is the angle at which the arc is curved.\
    `subdivisions x` defines the amount of subdivisons in `x`-direction.\
    `subdivisions y` defines the amount of subdivisons in `y`-direction.
    
### camera controls
`Visual` offers 6 camera controls:
* move up (`arrow up`)
* move down (`arrow down`)
* move left (`arrow left`)
* move right (`arrow right`)
* zoom in (`numpad+`)
* zoom out (`numpad-`)

### render controls
`Visual` offers the following render controls:
   * render wireframe (`w`) to render a border around each triangle
   * render grid (`g`) to render a grid in the background
   * render loads (`b`) to render the boundary conditions
   * render keybinds (`k`) to render all the keybinds in the bottom left corner
   * render mohr (`m`) to render the direction of the force

Furthermore, it offers different render-modes:
   * render nothing (`0`)
   * render stress (`1`) to render von-mises stress
   * render displacement (`2`) to render the displacement
   * render render E-Module (`3`) to render youngs-module
   * render stress x (`4`) to render the stress in `x-direction` aka. `sigma_x`
   * render stress y (`5`) to render the stress in `y-direction` aka. `sigma_y`
   * render stress xy (`6`) to render the stress in `xy-direction` aka. `tau`
   * render displacement x (`7`) to render the displacement in `x-direction`
   * render displacement y (`8`) to render the displacement in `y-direction`
   
The color-palette can be adjusted by tweaking the upper and lower limit: 
   * increase upper limit (`U`)
   * render stress (`Z`) to render von-mises stress
   * render displacement (`T`) to render the displacement
   * render render E-Module (`R`) to render youngs-module




