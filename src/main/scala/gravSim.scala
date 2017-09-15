import processing.core.PApplet

class gravSim extends PApplet {
		
	case class ball(x: Float, y: Float, xVect: Float, yVect: Float, col: (Int, Int, Int), lives : Int)
	val diam = 100
	val yMargin = 100
	val widthX = 1000 
	var floorLives = 100
	var ballList = (1 to 10).map { i =>
		var x = RNG(100,widthX-50)
		var y = RNG(50,yMargin)
		ball(x, y, RNG(-5,5), 0, (255, 255, 255),6)
	}
	
	override def settings() {
		size(widthX,widthX)
	}
	
	override def setup() {
		
	}
		
	override def draw(): Unit = {
		background(100)
		textSize(35)
		fill(255,255,255)
		text("Lives: " + floorLives, 750, 150)
		
		ballList = ballList.filter(_.lives > 0)
		
		textSize(80)
		
		if(ballList.size == 0) text("YOU WIN", widthX/2 - 200, widthX/2)
		if(floorLives <= 0) {
			text("YOU LOSE", widthX/2 - 200, widthX/2)
			noLoop()
		} 
		

		
		ballList.foreach{ b =>
			fill(b.col._1,b.col._2,b.col._3)
			ellipse(b.x, b.y, diam, diam)
			fill(0,0,0)
			textSize(35)
			text(b.lives, b.x-10, b.y-10) 
		}
		
		
		ballList = ballList.map {
		case ball(x, y, xVect, yVect, col, lives) if((y+diam) <= widthX && (y+diam) > 0) =>
			var gravity = 0.5f
			var xMov = xVect 
			if(x >= (widthX - diam)) {
				xMov = RNG(-3,-1)
			}
			if(x <= diam) {
				xMov  = RNG(1,3) 
			}
			ball(x+xVect, y+yVect, xMov, yVect+gravity, col, lives)
		
		case ball(x, y, xVect, yVect, col, lives) if((y+diam) > widthX) =>
			var bounceVal = -0.9f
			var xMov = xVect
			if(x >= (widthX - diam)) xMov = RNG(-2,0)
			if(x <= diam) xMov = RNG(0,2) 
			var gravity = 0.5f
			floorLives = floorLives - 1
			ball(x+xVect, y-20, xMov, yVect*bounceVal, col, lives)
		case ball(x, y, xVect, yVect, col, lives) if((y-diam) < 0) =>
			var bounceVal = -0.9f
			var xMov = xVect
			if(x >= (widthX - diam)) xMov = RNG(-2,0)
			if(x <= diam) xMov = RNG(0,2) 
			var gravity = 0.5f
			ball(x+xVect, y+20, xMov, yVect*bounceVal, col, lives)
		}
		
		ballList = ballList.map {
			case ball(x, y, xVect, yVect, col, lives) 
			if(mouseX >= x - diam/1.5 && mouseX <= x + diam/1.5 && mouseY >= y - diam/1.5 && mouseY <= y + diam/1.5) =>
				var bounceVal = -1.1f
				var xMov = xVect
				var rand = scala.util.Random
				var newCol = (rand.nextInt(255),rand.nextInt(255),rand.nextInt(255))
				ball(x, y- 50, xMov, yVect*bounceVal, newCol, lives - 1)
				
			case ball(x, y, xVect, yVect, col, lives) =>
				ball(x, y, xVect, yVect, col, lives)
		}
	}
	
	
	def RNG(min : Float, max : Float) : Float = {
		var random = new scala.util.Random()
		min + random.nextFloat()*max + 1
 	}

}

object gravSim extends App {
	PApplet.main("gravSim")
}

