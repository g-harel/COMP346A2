/**
 * Class BlockStack
 * Implements character block stack and operations upon it.
 *
 * $Revision: 1.4 $
 * $Last Revision Date: 2017/02/08 $
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca;
 * Inspired by an earlier code by Prof. D. Probst

 */
class BlockStack
{
	/**
	 * # of letters in the English alphabet + 2
	 */
	public static final int MAX_SIZE = 28;

	/**
	 * Default stack size
	 */
	public static final int DEFAULT_SIZE = 6;

	/**
	 * Current size of the stack
	 */
	private int iSize = DEFAULT_SIZE;

	/**
	 * Current top of the stack
	 */
	private int iTop  = 3;

	/**
	 * stack[0:5] with four defined values
	 */
	private char acStack[] = new char[] {'a', 'b', 'c', 'd', '$', '$'};

	/**
	 *
	 */
	private int stackAccessCounter = 0;

	/**
	 * Default constructor
	 */
	public BlockStack()
	{
	}

	/**
	 * Supplied size
	 */
	public BlockStack(final int piSize)
	{
		if(piSize != DEFAULT_SIZE)
		{
			this.acStack = new char[piSize];

			// Fill in with letters of the alphabet and keep
			// 2 free blocks
			for(int i = 0; i < piSize - 2; i++)
				this.acStack[i] = (char)('a' + i);

			this.acStack[piSize - 2] = this.acStack[piSize - 1] = '$';

			this.iTop = piSize - 3;
			this.iSize = piSize;
		}
	}

	/**
	 * Picks a value from the top without modifying the stack
	 * @return top element of the stack, char
	 */
	public char pick()
	{
		++stackAccessCounter;
		return this.acStack[this.iTop];
	}

	/**
	 * Returns arbitrary value from the stack array
	 * @return the element, char
	 */
	public char getAt(final int piPosition) throws BoundaryViolation
	{
		++stackAccessCounter;
		if (piPosition >= this.acStack.length || piPosition < 0) {
		    throw new BoundaryViolation("Requested index out of stack boundaries." + piPosition + " " + this.acStack.length);
    }
		return this.acStack[piPosition];
	}

	/**
	 * Standard push operation
	 */
	public void push(final char pcBlock) throws BoundaryViolation
	{
		++stackAccessCounter;
		if (this.isEmpty()) {
			this.acStack[0] = 'a';
		} else if (iSize == this.MAX_SIZE) {
		  throw new BoundaryViolation("Max stack size reached, cannot push more values");
    } else {
			this.acStack[++this.iTop] = pcBlock;
		}
	}

	/**
	 * Standard pop operation
	 * @return ex-top element of the stack, char
	 */
	public char pop() throws BoundaryViolation
	{
		++stackAccessCounter;
		if (this.isEmpty()) {
		    throw new BoundaryViolation("Cannot pop values from empty stack.");
    }
		char cBlock = this.acStack[this.iTop];
		this.acStack[this.iTop--] = '$'; // Leave prev. value undefined
		return cBlock;
	}

	public int getITop() {
		return this.iTop;
	}

	public int getISize() {
		return this.iSize;
	}

	public int getAccessCounter() {
		return this.stackAccessCounter;
	}

	public boolean isEmpty() {
		return (this.iTop == -1);
	}

    /**
     * custom exception to handle boundary violations
     */
    class BoundaryViolation extends Exception
    {
        public BoundaryViolation() {}

        public BoundaryViolation(String message)
        {
            super(message);
        }
    }

}

// EOF
